package com.icode.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icode.sys.domain.SysMenu;
import com.icode.sys.persistence.SysMenuMapper;
import com.icode.sys.service.SysMenuService;

@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {

	@Resource
	private SysMenuMapper sysMenuMapper;
	
	@Transactional
	public int insert(SysMenu sysMenu) {
		return sysMenuMapper.insert(sysMenu);
	}
	
	@Transactional
	public int update(SysMenu sysMenu) {
		return sysMenuMapper.updateByPrimaryKey(sysMenu);
	}
	
	@Transactional
	public int delete(Long tbid){
		return sysMenuMapper.deleteByPrimaryKey(tbid);
	}

	@Override
	public List<SysMenu> menusWithParent(Long menuid, Long userid) {
		
		List<SysMenu> menus = sysMenuMapper.menusWithParentUser(menuid,userid);
		for (int i = 0; i < menus.size(); i++) {
			SysMenu menu = menus.get(i);
			if(menu.getIsparent() == 1){
				List<SysMenu> childs = menusWithParent(menu.getTbid(),userid);
				menu.setChild(childs);
			}
		}
		return menus;
	}

	@Override
	public List<SysMenu> menusWithLeaf() {
		// 1.查询系统所有的叶子菜单
		List<SysMenu> menus = sysMenuMapper.menusWithLeaf();
		
		// 2.查询子菜单上的按钮
		for(SysMenu m : menus){
			List<SysMenu> child = sysMenuMapper.menusWithParent(m.getTbid());
			m.setChild(child);
		}
		return menus;
	}

	@Override
	public List<SysMenu> menusWithRole(Long roleid) {

		return sysMenuMapper.menusWithRole(roleid);
	}

	@Override
	public SysMenu menuParentWithTbid(Long menuid) {
		return sysMenuMapper.menuParentWithTbid(menuid);
	}

	@Override
	public SysMenu menuWithTbid(Long menuid) {
		return sysMenuMapper.menuWithTbid(menuid);
	}

}