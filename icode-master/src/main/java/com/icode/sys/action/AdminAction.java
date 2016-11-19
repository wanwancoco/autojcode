package com.icode.sys.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.icode.sys.domain.SysUser;
import com.icode.util.ResultJson;

@SuppressWarnings("serial")
@Controller("adminAction")
@Scope("prototype")
public class AdminAction extends BaseAction {

	private String backurl;
	
	private ResultJson resultJson;
	
	private int parentMenuId;
	
	/***
	 * 进入管理主界面
	 * @return
	 */
	public String index(){
		
		SysUser user = (SysUser)this.httpSession.getAttribute(SysUser.SYS_LOGIN_USER);
		//1、判断用户是否登录
		if(user == null){
			backurl = "login.jsp";
			return "result-jsp";
		}
		
		backurl = "index.jsp";
		return "result-jsp";
	}

	public String getBackurl() {
		return backurl;
	}

	public void setBackurl(String backurl) {
		this.backurl = backurl;
	}

	public ResultJson getResultJson() {
		return resultJson;
	}

	public void setResultJson(ResultJson resultJson) {
		this.resultJson = resultJson;
	}

	public int getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(int parentMenuId) {
		this.parentMenuId = parentMenuId;
	}
	
}
