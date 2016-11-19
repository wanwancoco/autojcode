<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
   String contextPath = request.getContextPath(); 
   request.setAttribute("contextPath", contextPath);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>AutoJCode-Java代码在线自动生成</title>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/docs.css" />
<link type="text/css" rel="stylesheet" href="${contextPath}/js/google-code-prettify/prettify.css" />
<link type="text/css" rel="stylesheet" href="${contextPath}/js/buttons/css/font-awesome.min.css" />
<link type="text/css" rel="stylesheet" href="${contextPath}/js/buttons/css/buttons.css" />
<link type="text/css" rel="stylesheet" href="${contextPath}/js/zTree/css/zTreeStyle/zTreeStyle.css" />
<style type="text/css">
a:hover{text-decoration:none;color:#fff;}
table{border-collapse:separate;}
#header {
    box-shadow: 0 1px 5px rgba(0, 0, 0, 0.25);
}
#dropShadowWrapper {
    background-image: url("${contextPath}/img/page_shadow_1014px.png");
    background-position: left center;
    background-repeat: repeat-y;
    border: 0 dashed pink;
    display: block;
    margin: auto;
    position: relative;
    width: 1014px;
    z-index: 2;
}
#footerWrapper{
    background-color:#fff;
    background-repeat: repeat-x;
    border: 0 dashed orange;
    color: #FFFFFF;
    margin: auto;
    min-height: 60px;
    width: 990px;
}
#headerWrapper {
    border: 0 dashed red;
    height: 100px;
    margin: auto;
    position: relative;
    width: 990px;
    z-index: 1000;
}
#logoWrapper {
    float: left;
    height: 90px;
    padding: 20px 0 0 22px;
    width: 308px;
}
#logoImg {
    border: 0 none;
}
#navigationWrapper {
    border: 0 dashed blue;
    float: right;
    height: 30px;
    margin-top: 21px;
    padding: 0;
    text-align: right;
    width: 630px;
}
#nav {
    border: 0 dashed red;
    list-style: none outside none;
    margin: 0;
    padding: 0;
    z-index: 450;
}
#nav li {
    display: block;
    float: left;
    margin: 0;
    position: relative;
    z-index: 500;
}
li.main_nav li {
    background: none repeat scroll 0 0 #C25E00;
    border: 0 dashed black;
}
.main_nav {
    border: 0 dashed pink;
}
#nav li a {
    border: 0 dashed purple;
    color: #333333;
    display: block;
    font-family: "Microsoft Yahei",Helvetica,Arial,Verdana,Sans-Serif;
    font-size: 18px;
    padding: 8px 24px 7px;
    text-align: left;
    text-decoration: none;
}
#nav li a:hover {
    background-color: #1F7D9C;
    color: #FFFFFF;
}
#nav a.selected {
    color: #FF0000;
}
#nav ul {
    display: none;
    left: 0;
    list-style: none outside none;
    padding: 0;
    position: absolute;
}
#nav ul li {
    float: left;
    text-align: left;
    width: 200px;
}
#nav ul a {
    border: 0 dashed green;
    color: #FFFFFF;
    display: block;
    padding: 2px 24px;
}
#nav ul a:hover {
    text-decoration: none;
}
#pageWrapper {
    background: none repeat scroll 0 0 transparent;
    border: 0 dashed red;
    overflow:hidden;
    position: relative;
}
#bannerWrapper{
	height: 312px;
    width: 990px;
}
h2 {
    background: url("${contextPath}/img/line.gif") repeat-x scroll center center transparent;
    margin: 15px 0 10px;
    text-align: center;
    color:#1F7D9C;
    font-family: "Microsoft Yahei",Helvetica,Arial,Verdana,Sans-Serif;
    font-size: 24px;
    font-weight:400;
    font-style:normal;
}
h2 span {
    background: none repeat scroll 0 0 #FFFFFF;
    padding: 0 20px;
}

.thumbnails {
    list-style: none outside none;
    margin-left: 5px;
}
.thumbnails:before, .thumbnails:after {
    content: "";
    display: table;
    line-height: 0;
}
.thumbnails:after {
    clear: both;
}
.row-fluid .thumbnails {
    margin-left: 0;
}
.thumbnails > li {
    float: left;
    margin-bottom: 20px;
    margin-left: 20px;
}
.thumbnail {
    border: 1px solid #DDDDDD;
    border-radius: 4px 4px 4px 4px;
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.055);
    display: block;
    line-height: 20px;
    padding: 4px;
    transition: all 0.2s ease-in-out 0s;
}
a.thumbnail:hover {
    border-color: #0088CC;
    box-shadow: 0 1px 4px rgba(0, 105, 214, 0.25);
}
.thumbnail > img {
    display: block;
    margin-left: auto;
    margin-right: auto;
    max-width: 100%;
}
.thumbnail .caption {
    color: #555555;
    padding: 9px;
}

.thumbnail {
    box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
    padding: 15px;
}
.thumbnail:hover {
    box-shadow: 0 5px 10px rgba(0, 0, 0, 0.5);
}
.thumbnail a img {
    height: auto;
    width: 100%;
}
.portfolio .thumbnail {
    padding: 8px;
}
/***SUBSTYLE***/
.bs-docs-example:after {
    background-color: #F5F5F5;
    border: 1px solid #DDDDDD;
    border-radius: 4px 0 4px 0;
    color: #9DA0A4;
    content: "业务类设计";
    font-size: 18px;
    font-weight: 400;
    font-family: "Microsoft Yahei",Helvetica,Arial,Verdana,Sans-Serif;
    left: -1px;
    padding: 3px 7px;
    position: absolute;
    top: -1px;
}
.prompt-span{
	float:none;
	color:#666;
}
.operate-img{
	cursor:pointer;
	display:block;
}

h5{
	color:#F67E27;
	font-weight:400;
	margin-top:0px;
}
#fileBrowser{
	width:260px;
	float:left;
}
#codeBrowser{
	width:800px;
	margin-left:10px;
	float:left;
}
.button{
	box-shadow:none;
}
</style>
</head>
<body>

   	<div id="pageWrapper">
		<div id="fileBrowser">
			<ul id="treeDemo" class="ztree"></ul>
			<a href="${contextPath}/FileDownload?filename=${filecode}" class="button glow button-rounded button-flat-action" style="margin-left:45px;margin-top:30px;">下载源代码</a>
			<a href="${contextPath}/FileDownload?filename=lib" class="button glow button-rounded button-flat-primary" style="margin-left:45px;margin-top:15px;">&nbsp;下载&nbsp;lib&nbsp;库</a>			
		</div>
		<div id="codeBrowser">
		<pre id="codePre" class="prettyprint linenums" style="border-radius:0px;min-height:500px;font-size:14px;margin-top:3px;margin-bottom:0px;">
			<s:property value="content" escape="true"/> 
		</pre>
		</div>		     
   	</div>
 	
<script type="text/javascript" src="${contextPath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/jquery.tmpl.js"></script>
<script type="text/javascript" src="${contextPath}/js/google-code-prettify/prettify.js"></script>
<script type="text/javascript" src="${contextPath}/js/zTree/js/jquery.ztree.core-3.5.js"></script>
<script id="attrTemplate" type="text/x-jquery-tmpl">
  <tr id="{{= trid}}">
    <td>{{= name}} <input type="hidden" class="attrName" name="attrName" value="{{= name}}"></td>
	<td>{{= type}} <input type="hidden" name="attrType" value="{{= type}}"></td>
	<td>{{html primaryImg}}<input type="hidden" name="attrPrimary" value="{{= primary}}"></td>
	<td>{{= comment}} <input type="hidden" name="attrComment" value="{{= comment}}"></td>
    <td class="operate-td"><img trid="{{= trid}}" title="删除属性"  class="operate-img" src="../img/delete_edit.gif" ></td>
  </tr>
</script>
<SCRIPT type="text/javascript">
<!--
var setting = {
	data: {
		simpleData: {
			enable: true
		}
	},
	callback: {
		onClick: onClick
	}
};

var zNodes =[
	{ id:1, pId:0, name:"src", open:true, icon:"${contextPath}/img/source-folder.gif"},
	{ id:11, pId:1, name:"${packageName}.action",open:true, icon:"${contextPath}/img/package_obj.gif"},
	{ id:110, pId:11, name:"${className}Action.java", icon:"${contextPath}/img/jcu_obj.gif"},
	{ id:12, pId:1, name:"${packageName}.service",open:true, icon:"${contextPath}/img/package_obj.gif"},
	{ id:120, pId:12, name:"${className}Service.java", icon:"${contextPath}/img/jcu_obj.gif"},
	{ id:13, pId:1, name:"${packageName}.service.impl",open:true, icon:"${contextPath}/img/package_obj.gif"},
	{ id:130, pId:13, name:"${className}ServiceImpl.java", icon:"${contextPath}/img/jcu_obj.gif"},
	{ id:14, pId:1, name:"${packageName}.persistence",open:true, icon:"${contextPath}/img/package_obj.gif"},
	{ id:140, pId:14, name:"${className}Mapper.java", icon:"${contextPath}/img/jcu_obj.gif"},
	{ id:141, pId:14, name:"${className}Mapper.xml", icon:"${contextPath}/img/xmldoc.gif"},
	{ id:15, pId:1, name:"${packageName}.domain",open:true, icon:"${contextPath}/img/package_obj.gif"},
	{ id:150, pId:15, name:"${className}.java", icon:"${contextPath}/img/jcu_obj.gif"},
	{ id:16, pId:1, name:"app-bean.xml", icon:"${contextPath}/img/xmldoc.gif"},
	{ id:17, pId:1, name:"app-config.xml", icon:"${contextPath}/img/xmldoc.gif"},
	{ id:18, pId:1, name:"struts.xml", icon:"${contextPath}/img/xmldoc.gif"},
	{ id:2, pId:0, name:"db", open:true ,icon:"${contextPath}/img/fldr_obj.gif"},
	{ id:21, pId:2, name:"${className}.sql", icon:"${contextPath}/img/sourceEditor.gif"},
	{ id:3, pId:0, name:"WebContent", open:true ,icon:"${contextPath}/img/fldr_obj.gif"},
	{ id:31, pId:3, name:"${folder}", open:true ,icon:"${contextPath}/img/fldr_obj.gif"},
	{ id:301, pId:31, name:"index.jsp",icon:"${contextPath}/img/sourceEditor.gif"},
	{ id:302, pId:31, name:"add.jsp",icon:"${contextPath}/img/sourceEditor.gif"},
	{ id:303, pId:31, name:"update.jsp",icon:"${contextPath}/img/sourceEditor.gif"},
	{ id:304, pId:31, name:"result.jsp",icon:"${contextPath}/img/sourceEditor.gif"}
];

function onClick(event, treeId, treeNode, clickFlag) {
	if(treeNode.name.indexOf(".java") != -1 || treeNode.name.indexOf(".jsp") != -1 || treeNode.name.indexOf(".xml") != -1 || treeNode.name.indexOf(".sql") != -1){
		var param = "?filecode=${filecode}&packageName=${packageName}&className=${className}&folder=${folder}&fileName="+treeNode.name;
		window.self.location = "${contextPath}/code/code_searchFileContent.action"+param;
	}
}

$(document).ready(function(){
	prettyPrint();
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	var i = 1;
	$(".pln:first").text("");
});
//-->
$(document).ready(function(){
	var h = $("#pageWrapper").height()+40;
	window.parent.changeCodeFrame(h);
});
</SCRIPT>
</body>
</html>