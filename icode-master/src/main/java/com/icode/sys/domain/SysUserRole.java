package com.icode.sys.domain;
 
import java.io.Serializable;
 
public class SysUserRole implements Serializable {
	
	private Long tbid;//
	  
	private Long userid;//
	  
	private Long roleid;//

	public Long getTbid() {
		return tbid;
	}

	public void setTbid(Long tbid) {
		this.tbid = tbid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}
	  
}