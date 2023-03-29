package com.shuishu.blog.common.config.security;


import com.shuishu.blog.common.config.security.authorization.DynamicAuthorizationManager;
import com.shuishu.blog.common.config.security.filter.EmailLoginFilter;
import com.shuishu.blog.common.config.security.filter.LocalLoginFilter;
import com.shuishu.blog.common.config.security.filter.LoginPolicyFilter;
import com.shuishu.blog.common.config.security.filter.PhoneLoginFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * @author ：谁书-ss
 * @date ：2022-12-29 21:59
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：SpringSecurity 配置
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {

    private final GlobalAuthenticationHandler globalAuthenticationHandler;
    /**
     * 不同的登录类型，可以创建不同类型的过滤器分别处理，
     * 也可以只创建一个 Filter：LoginFilter，然后根据不同类型的登录url，来创建不同的 Token
     */
    private final LocalLoginFilter localLoginFilter;
    private final EmailLoginFilter emailLoginFilter;
    private final PhoneLoginFilter phoneLoginFilter;
    private final LoginPolicyFilter loginPolicyFilter;
    private final DynamicAuthorizationManager dynamicAuthorizationManager;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // 路径配置 （authorizeRequests 方法已废弃，取而代之的是 authorizeHttpRequests）
        // http.antMatcher()不再可用，并被替换为 http.securityMatcher() 或 httpSecurity.requestMatchers()
        // SpringSecurityUtils.ignoreUrlArray() 可以只配置注册登录相关页面，其它所有权限放到数据库，
        // 通过 dynamicAuthorizationManager 动态权限决策管理器，来动态管理
        httpSecurity.authorizeHttpRequests()
                .requestMatchers(SpringSecurityUtils.ignoreUrlArray()).permitAll()
                .anyRequest()
                .access(dynamicAuthorizationManager);

        httpSecurity
                .formLogin().disable()
                .addFilterBefore(loginPolicyFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(localLoginFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(emailLoginFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(phoneLoginFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl(SpringSecurityUtils.LOGOUT_URL)
                .logoutSuccessHandler(globalAuthenticationHandler)
                //.and()
                //.rememberMe().rememberMeServices(dbRememberMeServices)
                .and()
                .csrf().disable()
                //.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                //.csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler())
                //.and()
                .exceptionHandling()
                .accessDeniedHandler(globalAuthenticationHandler)
                .authenticationEntryPoint(globalAuthenticationHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .oauth2Login();

        return httpSecurity.build();
    }

}
