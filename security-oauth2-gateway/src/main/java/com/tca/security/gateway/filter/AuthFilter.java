package com.tca.security.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.util.Base64Utils;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author zhoua
 * @Date 2020/6/6
 */
@Configuration
@Slf4j
public class AuthFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        // return true 表示会调用下面的run方法
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // 1.从context中获取authentication认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 2.JWT令牌有效，则会解析用户信息封装到OAuth2Authentication对象中
        if (!(authentication instanceof OAuth2Authentication)) {
            return null;
        }

        // 3.获取用户名
        Object principal = authentication.getPrincipal();
        // 4.获取权限
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Set<String> authoritySet = AuthorityUtils.authorityListToSet(authorities);
        // 5.获取详情
        Object details = authentication.getDetails();

        // 6.封装传输数据
        Map<String, Object> result = new HashMap<>(3);
        result.put("principal", principal);
        result.put("details", details);
        result.put("authorities", authoritySet);

        // 7.将认证数据放入请求头
        RequestContext currentContext = RequestContext.getCurrentContext();
        try {
            currentContext.addZuulRequestHeader("auth-token", Base64Utils.encodeToString(
                    JSONObject.toJSONString(result).getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            log.error("解析token出错", e);
        }
        return null;
    }
}
