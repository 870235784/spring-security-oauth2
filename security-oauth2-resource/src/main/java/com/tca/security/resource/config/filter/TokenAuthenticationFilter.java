package com.tca.security.resource.config.filter;

import com.alibaba.fastjson.JSONObject;
import com.tca.utils.ValidateUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Base64Utils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhoua
 * @Date 2020/6/7
 */
@Configuration
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // 从请求头获取auth-token
        String authTokenBase64 = httpServletRequest.getHeader("auth-token");

        if (ValidateUtils.isNotEmpty(authTokenBase64)) {
            String authTokenStr = new String(Base64Utils.decodeFromString(authTokenBase64), "UTF-8");
            JSONObject authToken = JSONObject.parseObject(authTokenStr, JSONObject.class);

            // 获取用户权限
            String authorities = ArrayUtils.toString(authToken.getJSONArray("authorities").toArray());
            // 获取用户名
            Object principal = authToken.get("principal");

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
                    UsernamePasswordAuthenticationToken(principal, null,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
            usernamePasswordAuthenticationToken.setDetails(authToken.get("details"));

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
