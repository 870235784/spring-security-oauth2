package com.tca.security.oauth2.web.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.tca.security.oauth2.web.entity.SysPermission;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author zhouan
 * @since 2020-04-16
 */
public interface ISysPermissionService extends IService<SysPermission> {


    /**
     * 根据用户id获取对应权限
     * @param userId
     * @return
     */
    List<SysPermission> getPermissionByUserId(Long userId);

    /**
     * 根据id递归删除
     * @param id
     * @return
     */
    boolean delete(Long id);
}
