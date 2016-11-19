package com.icode.sys.service;
import java.util.List;

import com.icode.sys.domain.SysMenu;

public interface SysMenuService {
	
	//Page search(Page pager);
	
	int insert(SysMenu sysMenu);

	int update(SysMenu sysMenu);
	
	int delete(Long tbid);

	List<SysMenu> menusWithParent(Long menuid,Long userid);

	List<SysMenu> menusWithLeaf();

	List<SysMenu> menusWithRole(Long tbid);

	SysMenu menuParentWithTbid(Long menuid);

	SysMenu menuWithTbid(Long menuid);
	
}