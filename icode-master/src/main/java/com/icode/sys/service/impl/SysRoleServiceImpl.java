package com.icode.sys.service.impl;
 
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icode.sys.domain.SysMenu;
import com.icode.sys.domain.SysRole;
import com.icode.sys.domain.SysRoleMenu;
import com.icode.sys.domain.SysUser;
import com.icode.sys.domain.SysUserRole;
import com.icode.sys.persistence.SysRoleMapper;
import com.icode.sys.service.SysMenuService;
import com.icode.sys.service.SysRoleService;
import com.icode.sys.service.SysUserService;
import com.icode.util.Pager;
 
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
 
	@Resource
	private SysRoleMapper sysRoleMapper;
	
	@Resource
	private SysUserService sysUserService;
	
	@Resource
	private SysMenuService sysMenuService;
	
	public SysRole searchById(Long tbid){
		return sysRoleMapper.selectByPrimaryKey(tbid);
	}
	
	@Transactional
	public int insert(SysRole sysRole, List<Long> userids,List<Long> menuids) {
		// 1.添加角色
		int res = sysRoleMapper.insert(sysRole);
		
		// 2.为角色添加用户
		for (Long userid : userids) {
			SysUserRole userRole = new SysUserRole();
			userRole.setRoleid(sysRole.getTbid());
			userRole.setUserid(userid);
			sysRoleMapper.insertRoleUser(userRole);
		}
		
		// 3.为角色添加访问菜单的权限
		List<Long> tbids = new ArrayList<Long>();//存放父菜单
		for (Long menuid : menuids) {
			SysRoleMenu roleMenu = new SysRoleMenu();
			roleMenu.setRoleid(sysRole.getTbid());
			roleMenu.setMenuid(menuid);
			sysRoleMapper.inserRoleMenu(roleMenu);
			
			SysMenu menu = sysMenuService.menuWithTbid(menuid);
			if(menu.getParentid().intValue() != 0 && menu.getType() == 1 ){
				getParentMenus(tbids,menuid);
			}
		}
		//添加访问父菜单的权限
		for(Long tbid : tbids){
			SysRoleMenu roleMenu = new SysRoleMenu();
			roleMenu.setRoleid(sysRole.getTbid());
			roleMenu.setMenuid(tbid);
			sysRoleMapper.inserRoleMenu(roleMenu);
		}
		return res;
	}
	
	private void getParentMenus(List<Long> menus, Long menuid) {
		SysMenu menu = sysMenuService.menuParentWithTbid(menuid);
		if(menu != null){
			if(!menus.contains(menu.getTbid())){
				menus.add(menu.getTbid());
			}
			if(menu.getParentid().intValue() != 0){
				getParentMenus(menus,menu.getTbid());
			}
		}
		
	}
	
	@Transactional
	public int delete(Long tbid){
		return sysRoleMapper.deleteByPrimaryKey(tbid);
	}

	@Override
	public PageInfo selectAll(int pagerNumber) {
		//1、获取角色信息 第1页，10条内容，默认查询总数count
		PageHelper.startPage(pagerNumber, Pager.ROLE_PAGE_NUM);
		List<SysRole> list = sysRoleMapper.selectAll();
		// 用PageInfo对结果进行包装
		PageInfo page = new PageInfo(list);
		// 获取使用每个角色的用户
		for(SysRole role : list){
			//2、获取角色的用户信息
			List<SysUser> users = sysUserService.userWithRole(role.getTbid());
			StringBuffer useridsb = new StringBuffer("");
			StringBuffer usernamesb = new StringBuffer("");
			for (int i = 0; i < users.size(); i++) {
				SysUser sysUser = users.get(i);
				useridsb.append(sysUser.getTbid());
				usernamesb.append(sysUser.getRealname());
				if(i != (users.size()-1)){
					useridsb.append(",");
					usernamesb.append(",");
				}
			}
			role.setUserids(useridsb.toString());
			role.setUserStr(usernamesb.toString());
			
			//3、获取角色的权限信息
			List<SysMenu> menus = sysMenuService.menusWithRole(role.getTbid());
			StringBuffer menuidsb = new StringBuffer("");
			for (int i = 0; i < menus.size(); i++) {
				SysMenu sysMenu = menus.get(i);
				menuidsb.append(sysMenu.getTbid());
				if(i != (menus.size()-1)){
					menuidsb.append(",");
				}
			}
			role.setMenuids(menuidsb.toString());
		}
		
		return page;
	}

	@Override
	public int update(SysRole sysRole, List<Long> userids, List<Long> menuids) {
		// 1.删除角色用户，权限信息信息
		int res = 1;
		sysRoleMapper.deleteRoleUser(sysRole.getTbid());
		sysRoleMapper.deleteRoleMenu(sysRole.getTbid());
		
		// 修改角色名称
		sysRoleMapper.updateByPrimaryKey(sysRole);

		// 2.为角色添加用户
		for (Long userid : userids) {
			SysUserRole userRole = new SysUserRole();
			userRole.setRoleid(sysRole.getTbid());
			userRole.setUserid(userid);
			sysRoleMapper.insertRoleUser(userRole);
		}

		// 3.为角色添加访问菜单的权限
		List<Long> tbids = new ArrayList<Long>();// 存放父菜单
		for (Long menuid : menuids) {
			SysRoleMenu roleMenu = new SysRoleMenu();
			roleMenu.setRoleid(sysRole.getTbid());
			roleMenu.setMenuid(menuid);
			sysRoleMapper.inserRoleMenu(roleMenu);

			SysMenu menu = sysMenuService.menuWithTbid(menuid);
			if (menu.getParentid().intValue() != 0 && menu.getType() == 1) {
				getParentMenus(tbids, menuid);
			}
		}
		// 添加访问父菜单的权限
		for (Long tbid : tbids) {
			SysRoleMenu roleMenu = new SysRoleMenu();
			roleMenu.setRoleid(sysRole.getTbid());
			roleMenu.setMenuid(tbid);
			sysRoleMapper.inserRoleMenu(roleMenu);
		}
		return res;
	}

	@Transactional
	public int delete(List<Long> roleids) {
		int res = 1;
		try {
			sysRoleMapper.deleteRoleMenus(roleids);
			sysRoleMapper.deleteRoleUsers(roleids);
			res = sysRoleMapper.deleteBatch(roleids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public List<SysRole> rolesWithUser(Long userid) {
		return sysRoleMapper.rolesWithUser(userid);
	}


 
}