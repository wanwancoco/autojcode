package com.icode.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icode.sys.domain.SysRole;
import com.icode.sys.domain.SysUser;
import com.icode.sys.persistence.SysUserMapper;
import com.icode.sys.service.SysRoleService;
import com.icode.sys.service.SysUserService;
import com.icode.util.Pager;

@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

	@Resource
	private SysUserMapper sysUserMapper;
	
	@Resource
	private SysRoleService sysRoleService;
	
	public SysUser userWithId(Long tbid){
		return sysUserMapper.userWithId(tbid);
	}
	
	@Transactional
	public int insert(SysUser sysUser) {
		return sysUserMapper.insert(sysUser);
	}
	
	@Transactional
	public int delete(Long tbid){
		return sysUserMapper.deleteByPrimaryKey(tbid);
	}
	
	@Transactional
	public int update(SysUser sysUser) {
		return sysUserMapper.updateByPrimaryKey(sysUser);
	}

	@Override
	public SysUser userWithUsername(String username) {
		return sysUserMapper.userWithUsername(username);
	}

	@Override
	public List<SysUser> usersWithType(int type) {
		List<SysUser> users = sysUserMapper.usersWithType(type);
		return users;
	}

	@Override
	public List<SysUser> userWithRole(Long roleid) {
		return sysUserMapper.userWithRole(roleid);
	}

	@Override
	public PageInfo usersWithType(int pagerNumber, int type) {
		
		//1、获取系统管理员信息 第1页，10条内容，默认查询总数count
		PageHelper.startPage(pagerNumber, Pager.PAGE_NUM);
		List<SysUser> list = sysUserMapper.usersWithType(type);
		
		for(SysUser sysUser : list){
			List<SysRole> roles = sysRoleService.rolesWithUser(sysUser.getTbid());
			StringBuffer roleStr = new StringBuffer("");
			for(int i = 0; i<roles.size(); i++){
				SysRole r = roles.get(i);
				roleStr.append(r.getRolename());
				if( i < (roles.size() - 1) ){
					roleStr.append(",");
				}
			}
			sysUser.setRoleStr(roleStr.toString());
		}
		// 用PageInfo对结果进行包装
		PageInfo page = new PageInfo(list);
		return page;
	}

	@Override
	public int updateRealName(SysUser sysUser) {
		return sysUserMapper.updateRealName(sysUser);
	}

	@Override
	public int cancelAdmin(List<Long> userids) {
		for (int i = 0; i < userids.size(); i++) {
			sysUserMapper.updateType(userids.get(i));
		}
		return 1;
	}

}