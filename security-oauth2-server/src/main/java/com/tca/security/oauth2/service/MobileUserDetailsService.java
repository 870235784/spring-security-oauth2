package com.tca.security.oauth2.service;

import com.tca.security.oauth2.web.entity.SysUser;
import com.tca.security.oauth2.web.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhoua
 * @Date 2020/3/25
 */
@Service("mobileUserDetailService")
public class MobileUserDetailsService extends AbstractUserDetailsService {

    @Autowired
    private ISysUserService sysUserService;

    @Override
    public SysUser getUserByPrincipal(String principal) {
        return sysUserService.getUserByMobile(principal);
    }
}
