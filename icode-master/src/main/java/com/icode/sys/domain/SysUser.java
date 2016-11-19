package com.icode.sys.domain;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class SysUser implements Serializable {
	
	public static final String SYS_LOGIN_USER = "sysLoginUser";
	
	private Long tbid;//用户编号
	  
	private String username;//登录账号
	  
	private String password;//加密密码
	  
	private Integer type;//用户类型  1：管理员  0 ：普通用户  2：系统特殊用户 3：商家
	  
	private String nick;//用户昵称
	  
	private String headimg;//头像地址
	  
	private String telephone;//手机号
	  
	private String email;//邮箱
	  
	private String sex;//性别
	  
	private String home;//所在地
	  
	private String live;//gps位置
	  
	private String signname;//签名
	  
	private String loginx;//登录 x坐标
	  
	private String loginy;//登录 y坐标
	  
	private Integer mispublish;//是否公开地理信息 让附近的人中查询出来 1 : 公开 0：不公开
	  
	private Integer execute;//被管理员执行的操作 0:没操作 1:禁止临时会话 2:禁止登陆 3:永久封
	  
	private Integer apply;//是否申请解封 0:没申请或者申请失败 1:申请成功
	  
	private Integer status;//状态 1:在线 0: 离线
	
	/**用户 detail表中属性 **/
	private String realname;
	
	private String roleStr;//职务信息
	  
	public Long getTbid() {
		 return tbid;
	}
	
	public void setTbid(Long tbid) {
		 this.tbid = tbid;
	}
	
	public String getUsername() {
		 return username;
	}
	
	public void setUsername(String username) {
		 this.username = username;
	}
	
	public String getPassword() {
		 return password;
	}
	
	public void setPassword(String password) {
		 this.password = password;
	}
	
	public Integer getType() {
		 return type;
	}
	
	public void setType(Integer type) {
		 this.type = type;
	}
	
	public String getNick() {
		 return nick;
	}
	
	public void setNick(String nick) {
		 this.nick = nick;
	}
	
	public String getHeadimg() {
		 return headimg;
	}
	
	public void setHeadimg(String headimg) {
		 this.headimg = headimg;
	}
	
	public String getTelephone() {
		 return telephone;
	}
	
	public void setTelephone(String telephone) {
		 this.telephone = telephone;
	}
	
	public String getEmail() {
		 return email;
	}
	
	public void setEmail(String email) {
		 this.email = email;
	}
	
	public String getSex() {
		 return sex;
	}
	
	public void setSex(String sex) {
		 this.sex = sex;
	}
	
	public String getHome() {
		 return home;
	}
	
	public void setHome(String home) {
		 this.home = home;
	}
	
	public String getLive() {
		 return live;
	}
	
	public void setLive(String live) {
		 this.live = live;
	}
	
	public String getSignname() {
		 return signname;
	}
	
	public void setSignname(String signname) {
		 this.signname = signname;
	}
	
	public String getLoginx() {
		 return loginx;
	}
	
	public void setLoginx(String loginx) {
		 this.loginx = loginx;
	}
	
	public String getLoginy() {
		 return loginy;
	}
	
	public void setLoginy(String loginy) {
		 this.loginy = loginy;
	}
	
	public Integer getMispublish() {
		 return mispublish;
	}
	
	public void setMispublish(Integer mispublish) {
		 this.mispublish = mispublish;
	}
	
	public Integer getExecute() {
		 return execute;
	}
	
	public void setExecute(Integer execute) {
		 this.execute = execute;
	}
	
	public Integer getApply() {
		 return apply;
	}
	
	public void setApply(Integer apply) {
		 this.apply = apply;
	}
	
	public Integer getStatus() {
		 return status;
	}
	
	public void setStatus(Integer status) {
		 this.status = status;
	}

	public String getRealname() {
		if(StringUtils.isEmpty(realname)){
			return username;
		}
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getRoleStr() {
		return roleStr;
	}

	public void setRoleStr(String roleStr) {
		this.roleStr = roleStr;
	}
	
}