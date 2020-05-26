package com.tca.security.oauth2.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tca.security.oauth2.web.entity.SysPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author zhouan
 * @since 2020-04-16
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 根据用户id获取对应权限
     * @param userId
     * @return
     */
    List<SysPermission> getPermissionByUserId(@Param("userId") Long userId);

    /**
     * 根据角色id获取对应权限
     * @param roleId
     * @return
     */
    List<SysPermission> getPermissionByRoleId(@Param("roleId") Long roleId);
}
