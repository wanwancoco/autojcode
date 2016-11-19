package com.icode.sys.service;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.icode.sys.domain.SysUser;

public interface SysUserService {

	SysUser userWithId(Long tbid);
	
	//Page search(Page pager);
	
	int insert(SysUser sysUser);

	int update(SysUser sysUser);
	
	int delete(Long tbid);

	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	SysUser userWithUsername(String username);
	/**
	 * 根据用户类型查找用户
	 * @param type
	 * @return
	 */
	List<SysUser> usersWithType(int type);
	/***
	 * 根据用户角色信息查找用户
	 * @param roleid
	 * @return
	 */
	List<SysUser> userWithRole(Long roleid);

	PageInfo usersWithType(int pagerNumber, int type);

	int updateRealName(SysUser sysUser);

	int cancelAdmin(List<Long> userids);
	
}