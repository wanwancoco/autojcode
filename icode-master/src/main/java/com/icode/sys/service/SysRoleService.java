package com.icode.sys.service;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.icode.sys.domain.SysRole;
 
public interface SysRoleService {
 
	SysRole searchById(Long tbid);
	
	PageInfo selectAll(int pagerNumber);
	
	/***
	 * 添加角色 并且授权
	 * @param sysRole
	 * @param userids
	 * @param menuids
	 * @return
	 */
	int insert(SysRole sysRole, List<Long> userids, List<Long> menuids);
 
	int update(SysRole sysRole, List<Long> userids, List<Long> menuids);
	
	int delete(List<Long> roleids);

	List<SysRole> rolesWithUser(Long tbid);
	
}
 
