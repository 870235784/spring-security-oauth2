package com.tca.security.oauth2.web.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tca.security.oauth2.web.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author zhouan
 * @since 2020-04-16
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 批量插入role-permission关联关系
     * @param roleId
     * @param perIds
     */
    void insertBatchRolePermissionRel(@Param("roleId") Long roleId, @Param("perIds") List<Long> perIds);

    /**
     * 根据roleId删除role-permission关联关系
     * @param roleId
     */
    void deleteRolePermissionRelByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据userId获取角色
     * @param userId
     * @return
     */
    List<SysRole> getRoleByUserId(@Param("userId") Long userId);

    /**
     * 批量插入user-role关联关系
     * @param userId
     * @param roleIds
     */
    void insertBatchUserRoleRel(@Param("userId") Long userId, @Param("roleIds") List<Long> roleIds);

    /**
     * 根据userId删除user-role关联关系
     * @param userId
     */
    void deleteUserRoleRelByUserId(@Param("userId") Long userId);
}
