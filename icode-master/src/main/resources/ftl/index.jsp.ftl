<#macro fieldTh attrs>
	<#list attrs as attr>
					<th>${attr.attrName}</th>
	</#list>
</#macro>
<#macro fieldTd attrs>
	<#list attrs as attr>
					<td>${r"$"}{${attr.attrName}}</td>
	</#list>
</#macro>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
   String contextPath = request.getContextPath(); 
   request.setAttribute("contextPath", contextPath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${model.className}</title>
<link type="text/css" rel="stylesheet" href="${r"${contextPath}"}/css/bootstrap.min.css" >
<link type="text/css" rel="stylesheet" href="${r"${contextPath}"}/css/bootstrap-responsive.min.css" >
<link type="text/css" rel="stylesheet" href="${r"${contextPath}"}/plugin/buttons/css/font-awesome.min.css" />
<link type="text/css" rel="stylesheet" href="${r"${contextPath}"}/plugin/buttons/css/buttons.css" />
<style type="text/css">
a:hover{color:white;text-decoration:none;}
table{border-collapse:separate;}
</style>
</head>
<body>
   <div class="container" style="margin-top:30px;">
    	<fieldset>
		    <legend>${model.className}</legend>
		    <div>
		    	<a id="add-btn" href="#" class="button button-rounded button-flat-action">添加</a>
		    </div>
		    <table class="table table-hover">
              <thead>
                <tr>
                  <@fieldTh attrs=model.attrs/>
                </tr>
              </thead>
              <tbody>
              	<s:iterator var="item" value="#request.pager.datas">
                	<tr>
                  		<@fieldTd attrs=model.attrs/>
	                  	<td>
	                  		<a href="#" tbid="${r"${tbid}"}" class="button button-rounded button-flat-primary">修改</a>
	                  		<a href="#" tbid="${r"${tbid}"}" class="button button-rounded button-flat-highlight">删除</a>
	                  	</td>
                	</tr>
                 </s:iterator>
              </tbody>
            </table>
            <s:if test="%{#request.pager.total > 0}">
					<!-- 分页 -->
					<div id="pagination" class="pagination" >
						<ul>
							<pg:pager url="${r"${contextPath}"}/front/${model.className?lower_case}_index.action" items="${r"${pager.total}"}"	
								export="currentPageNumber=pageNumber">
								<pg:first>
										<li class="" ><a  id="firstPage" href="${r"${pageUrl}"}" >首页</a></li>
								</pg:first>
								<pg:prev>
									<li ><a id="nextPage" href="${r"${pageUrl}"}" >上一页</a></li>
								</pg:prev>
								<pg:pages>
									<c:choose> 
										<c:when test="${r"${currentPageNumber == pageNumber}"}">
											<li class="active"><a href="#"><s:property value="pageNumber"/>${r"${pageNumber}"} </a></li>
										</c:when>
										<c:otherwise>
											<li class=""><a href="${r"${pageUrl}"}">${r"${pageNumber}"}</a></li>
										</c:otherwise>
									</c:choose>
								</pg:pages>
								<pg:next>
									<li><a id="nextPage" href="${r"${pageUrl}"}" >下一页</a></li>
								</pg:next>
								<pg:last>
										<li><a id="endPage" href="${r"${pageUrl}"}" >尾页</a></li>
								</pg:last>
							</pg:pager>
						</ul>
					</div>
				   <!-- /分页 -->
				</s:if>
		  </fieldset>
   </div><!-- container end -->
<script type="text/javascript" src="${r"${contextPath}"}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${r"${contextPath}"}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${r"${contextPath}"}/plugin/lhgdialog/lhgdialog.js?skin=mac"></script>
<script>
$(function(){
	$("#add-btn").click(function(){
		window.self.location = "${r"${contextPath}"}/${model.className?lower_case}/add.jsp";
	});
	$(".button-flat-highlight").click(function(){
		var tbid = $(this).attr("tbid");
		$.dialog({
			title:"确认删除",
			id:"delDialog",
		    lock: true,
		    content: '删除后信息后，信息将不再列表中显示。',
		    icon: 'prompt.gif',
		    time: 18,
		    ok: function () {
		    	/**确认信息*/
		    	$.post("${r"${contextPath}"}/front/${model.className?lower_case}_delete.action",{
					"${model.className?uncap_first}.tbid":tbid
				},function(msg){
					if(msg == 1){
						refresh();
					}
				});
		    },
		    cancel: true,
		    width:280
		});
	});
	$(".button-flat-primary").click(function(){
		var tbid = $(this).attr("tbid");
		window.self.location = "${r"${contextPath}"}/front/${model.className?lower_case}_intoUpdate.action?${model.className?uncap_first}.tbid="+tbid;
	});
});
//刷新当前页面
function refresh(){
	window.self.location = "${r"${contextPath}"}/front/${model.className?lower_case}_index.action?pager.offset=${r"${pager.offset}"}";
}
</script>
</body>
</html>