package com.icode.sys.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.icode.sys.domain.SysMenu;

public interface SysMenuMapper {
	
	//添加
    int insert(SysMenu sysMenu);
    
    //删除
    int deleteByPrimaryKey(@Param(value="tbid")Long tbid);

    //修改
    int updateByPrimaryKey(SysMenu sysMenu);

    /**
     * 根据父菜单获取菜单列表
     * @param menuid
     * @return
     */
	List<SysMenu> menusWithParent(Long tbid);
	/**
	 * 根据父菜单及用户编号获取菜单列表
	 * @param menuid
	 * @param userid
	 * @return
	 */
	List<SysMenu> menusWithParentUser(@Param(value="menuid")Long menuid,@Param(value="userid")Long userid);
	/***
	 * 获取叶子菜单
	 * @return
	 */
	List<SysMenu> menusWithLeaf();
    /***
     * 获取角色的菜单信息
     * @param roleid
     * @return
     */
	List<SysMenu> menusWithRole(Long roleid);

	/**
	 * 根据菜单编号获取父菜单信息
	 * @param menuid
	 * @return
	 */
	SysMenu menuParentWithTbid(Long menuid);
    /***
     * 根据菜单编号获取菜单信息
     * @param tbid
     * @return
     */
	SysMenu menuWithTbid(Long tbid);
	
}