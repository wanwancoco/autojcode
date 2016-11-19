package com.icode.sys.domain;

import java.io.Serializable;
import java.util.List;

import com.icode.util.DecUtil;

public class SysMenu implements Serializable {
	
	private Long tbid;//
	  
	private String name;//
	  
	private String url;//菜单地址
	  
	private Integer isparent;//是否为父级菜单
	  
	private Long parentid;//所尾父级菜单id
	  
	private Integer index;//同级菜单中的顺序
	  
	private Integer type;//1：菜单 2：按钮
	  
	private Integer show;//是否展现
	
	private List<SysMenu> child;
	
	private String icon;
	
	private String sysflag;
	
	private String menucode;
	
	public Long getTbid() {
		return tbid;
	}

	public void setTbid(Long tbid) {
		this.tbid = tbid;
	}

	public Long getParentid() {
		return parentid;
	}

	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}

	public String getName() {
		 return name;
	}
	
	public void setName(String name) {
		 this.name = name;
	}
	
	public String getUrl() {
		 return url;
	}
	
	public void setUrl(String url) {
		 this.url = url;
	}
	
	public Integer getIsparent() {
		 return isparent;
	}
	
	public void setIsparent(Integer isparent) {
		 this.isparent = isparent;
	}
	
	public Integer getIndex() {
		 return index;
	}
	
	public void setIndex(Integer index) {
		 this.index = index;
	}
	
	public Integer getType() {
		 return type;
	}
	
	public void setType(Integer type) {
		 this.type = type;
	}
	
	public Integer getShow() {
		 return show;
	}
	
	public void setShow(Integer show) {
		 this.show = show;
	}

	public List<SysMenu> getChild() {
		return child;
	}

	public void setChild(List<SysMenu> child) {
		this.child = child;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getSysflag() {
		return sysflag;
	}

	public void setSysflag(String sysflag) {
		this.sysflag = sysflag;
	}

	public String getMenucode(){
		String code = "";
		try {
			DecUtil util = new DecUtil();  
			String msg =tbid+"";  
			code = util.encrypt(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return code;
	}

	public void setMenucode(String menucode) {
		this.menucode = menucode;
	}
	
}