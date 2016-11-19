package com.icode.sys.persistence;
 
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.icode.sys.domain.SysRole;
import com.icode.sys.domain.SysRoleMenu;
import com.icode.sys.domain.SysUserRole;
 
public interface SysRoleMapper {
	
    int deleteByPrimaryKey(@Param(value="tbid")Long tbid);
 
    int insert(SysRole sysRole);
 
    SysRole selectByPrimaryKey(@Param(value="tbid")Long tbid);
 
    int updateByPrimaryKey(SysRole sysRole);

	List<SysRole> selectAll();

	void insertRoleUser(SysUserRole userRole);

	void inserRoleMenu(SysRoleMenu roleMenu);

	int deleteRoleUser(Long roleid);

	int deleteRoleMenu(Long roleid);

	int deleteBatch(List<Long> roleids);

	void deleteRoleUsers(List<Long> roleids);

	void deleteRoleMenus(List<Long> roleids);

	List<SysRole> rolesWithUser(Long userid);
 
}