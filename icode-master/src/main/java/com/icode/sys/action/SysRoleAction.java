package com.icode.sys.action;
 
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.github.pagehelper.PageInfo;
import com.icode.sys.domain.SysMenu;
import com.icode.sys.domain.SysRole;
import com.icode.sys.domain.SysUser;
import com.icode.sys.service.SysMenuService;
import com.icode.sys.service.SysRoleService;
import com.icode.sys.service.SysUserService;
import com.icode.util.ResultJson;
 
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class SysRoleAction extends BaseAction {
 
	private SysRole sysRole;
	
	private PageInfo pageInfo;
	
	private int pageNumber;
	
	private ResultJson resultJson;
	
	private String backurl;
	
	private List<SysUser> users;
	
	private List<SysMenu> menus;
	
	private List<Long> userids;
	
	private List<Long> menuids;
	
	private List<Long> roleids;
	
	@Resource
	private SysRoleService sysRoleService;
	
	@Resource
	private SysUserService sysUserService;
	
	@Resource
	private SysMenuService sysMenuService;
 
	public String index(){
		// 1.获取所有的角色信息
		pageInfo = sysRoleService.selectAll(pageNumber);
		
		// 2.获取系统管理员信息
		users = sysUserService.usersWithType(1);
		
		// 3.获取系统叶子菜单信息
		menus = sysMenuService.menusWithLeaf();
		
		backurl = "/admin/user_role.jsp";
		
		return "result-jsp";
	}
	
	public String add(){
		int result = sysRoleService.insert(sysRole,userids,menuids);
		resultJson = new ResultJson(result);
		return "result-json";
	}
	
	public String delete(){
		int result = sysRoleService.delete(roleids);
		resultJson = new ResultJson(result);
		return "result-json";
	}
	
	public String intoUpdate(){
		sysRole = sysRoleService.searchById(sysRole.getTbid());
		return "intoUpdate";
	}
	
	public String update(){
		int result = sysRoleService.update(sysRole,userids,menuids);
		resultJson = new ResultJson(result);
		return "result-json";
	}
	
	public SysRole getSysRole(){
		return sysRole;
	}
	public void setSysRole(SysRole sysRole){
		this.sysRole = sysRole;
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
	public List<SysUser> getUsers() {
		return users;
	}
	public void setUsers(List<SysUser> users) {
		this.users = users;
	}
	public List<SysMenu> getMenus() {
		return menus;
	}
	public void setMenus(List<SysMenu> menus) {
		this.menus = menus;
	}

	public List<Long> getUserids() {
		return userids;
	}

	public void setUserids(List<Long> userids) {
		this.userids = userids;
	}

	public List<Long> getMenuids() {
		return menuids;
	}

	public void setMenuids(List<Long> menuids) {
		this.menuids = menuids;
	}

	public ResultJson getResultJson() {
		return resultJson;
	}

	public void setResultJson(ResultJson resultJson) {
		this.resultJson = resultJson;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public List<Long> getRoleids() {
		return roleids;
	}

	public void setRoleids(List<Long> roleids) {
		this.roleids = roleids;
	}
	
}