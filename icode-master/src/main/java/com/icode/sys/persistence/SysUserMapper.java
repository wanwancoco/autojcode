package com.icode.sys.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.icode.sys.domain.SysUser;

public interface SysUserMapper {
	
    int insert(SysUser sysUser);
    
    int deleteByPrimaryKey(@Param(value="tbid")Long tbid);

    int updateByPrimaryKey(SysUser sysUser);

    SysUser userWithId(@Param(value="tbid")Long tbid);
    
	SysUser userWithUsername(String username);

	List<SysUser> usersWithType(int type);

	List<SysUser> userWithRole(Long roleid);

	int updateRealName(SysUser sysUser);

	int updateType(Long tbid);
	
}