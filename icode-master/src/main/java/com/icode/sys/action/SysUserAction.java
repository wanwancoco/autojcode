package com.icode.sys.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.github.pagehelper.PageInfo;
import com.icode.sys.domain.SysUser;
import com.icode.sys.service.SysUserService;
import com.icode.util.ResultJson;
import com.icode.util.SHATool;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class SysUserAction extends BaseAction {

	private SysUser sysUser;
	
	private PageInfo pageInfo;
	
	private ResultJson resultJson;
	
	private String backurl;
	
	private int pageNumber;
	
	private List<Long> userids;
	
	@Resource
	private SysUserService sysUserService;
	
	public String login(){
		int result = 3;
		if(sysUser != null && StringUtils.isNotEmpty(sysUser.getUsername())){
			SysUser tempUser = sysUserService.userWithUsername(sysUser.getUsername());
			
			if(tempUser != null){
				String pwdcode = new SHATool().getDigestOfString(sysUser.getPassword().trim().getBytes());
				if(pwdcode.equals(tempUser.getPassword())){
					result = 1;
					this.httpSession.setAttribute(SysUser.SYS_LOGIN_USER, tempUser);
				}else{//密码不正确
					result = 2;
				}
			}else{ //用户名不存在
				result = 3;
			}
		}
		resultJson = new ResultJson(result);
		return "result-json";
	}
	
	public String exit(){
		this.httpSession.setAttribute(SysUser.SYS_LOGIN_USER, null);
		backurl = "/admin/login.jsp";
		return "result-jsp";
	}

	public String index(){
		//pager = sysUserService.search(pager);
		return "index";
	}
	
	public String initAdmin(){
		
		// 1.获取所有的管理员信息
		pageInfo = sysUserService.usersWithType(pageNumber,1);
				
		backurl = "/admin/user_admin.jsp";
		return "result-jsp";
	}
	
	public String initUser(){
		sysUser = new SysUser();
		sysUser.setUsername("liuchao");
		// 1.获取所有的普通用户信息
		pageInfo = sysUserService.usersWithType(pageNumber,0);
				
		backurl = "/admin/user_normal.jsp";
		return "result-jsp";
	}
	
	public String add(){
		int result = sysUserService.insert(sysUser);
		resultJson = new ResultJson(result);
		backurl = "sysUser/add.jsp";
		return "result-jsp";
	}
	
	public String cancelAdmin(){
		int result = sysUserService.cancelAdmin(userids);
		resultJson = new ResultJson(result);
		return "result-json";
	}
	
	public String delete(){
		int result = sysUserService.delete(sysUser.getTbid());
		resultJson = new ResultJson(result);
		return "result-json";
	}
	
	public String intoUpdate(){
		sysUser = sysUserService.userWithId(sysUser.getTbid());
		return "intoUpdate";
	}
	
	public String update(){
		int result = sysUserService.update(sysUser);
		resultJson = new ResultJson(result);
		backurl = "front/sysUser_intoUpdate.action?sysUser.tbid="+sysUser.getTbid();
		return "result-jsp";
	}
	
	public String updateRealName(){
		int result = sysUserService.updateRealName(sysUser);
		resultJson = new ResultJson(result);
		return "result-json";
	}
	
	public SysUser getSysUser(){
		return sysUser;
	}
	public void setSysUser(SysUser sysUser){
		this.sysUser = sysUser;
	}
	
	public ResultJson getResultJson() {
		return resultJson;
	}

	public void setResultJson(ResultJson resultJson) {
		this.resultJson = resultJson;
	}

	public String getBackurl() {
		return backurl;
	}
	public void setBackurl(String backurl) {
		this.backurl = backurl;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public List<Long> getUserids() {
		return userids;
	}

	public void setUserids(List<Long> userids) {
		this.userids = userids;
	}
	
}