#### 认证服务
##### 1.配置被允许访问此认证服务器的客户端详情信息
###### step1 创建 AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter,加上 @Configuration, @EnableAuthorizationServer 注解
###### step2 重写 public void configure(ClientDetailsServiceConfigurer clients) throws Exception 方法

##### 2.使用密码的授权方式
###### step1 创建 SpringSecurityWebConfig extends WebSecurityConfigurerAdapter, 加上@EnableWebSecurity
###### step2 向 spring容器中注入 AuthenticationManager
```java
@Bean
@Override
public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
}
```
###### step3 修改 AuthorizationServerConfig, 使用密码模式
```java
@Autowired
private AuthenticationManager authenticationManager;
@Override
public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    // 密码模式要设置认证管理器
    endpoints.authenticationManager(authenticationManager)
}
```
##### 3.解决refresh_token报错的问题 (internal server error)
###### step1 创建 CustomUserDetailsService implements UserDetailsService
###### step2 修改 SpringSecurityWebConfig
```java
@Autowired
private CustomUserDetailsService customUserDetailsService;
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(customUserDetailsService);
}
```
###### step3 修改AuthorizationServerConfig
```java
@Autowired
private CustomUserDetailsService customUserDetailsService;
@Override
public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.authenticationManager(authenticationManager);
    endpoints.userDetailsService(customUserDetailsService);
}
```

##### 4.Token管理 -- TokenStore
TokenStore (interface)
InMemoryTokenStore (默认实现)
RedisTokenStore
JdbcTokenStore
JwtTokenStore
###### 4.1 实现RedisTokenStore
###### 4.1.1 step1 添加 springboot-redis 依赖
###### 4.1.2 step2 创建 TokenConfig, 添加相关配置
```java
@Autowired
private RedisConnectionFactory redisConnectionFactory;
@Bean
public TokenStore tokenStore() {
    return new RedisTokenStore(redisConnectionFactory);
}
```
###### 4.1.3 step3 修改 AuthorizationServerConfig
```java
@Autowired
private TokenStore tokenStore;
@Override
public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.authenticationManager(authenticationManager);
    endpoints.userDetailsService(customUserDetailsService);
    endpoints.tokenStore(tokenStore);
}
```