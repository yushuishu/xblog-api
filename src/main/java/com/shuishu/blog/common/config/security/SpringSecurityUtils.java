package com.shuishu.blog.common.config.security;


import com.google.common.collect.Lists;
import com.shuishu.blog.common.config.exception.BusinessException;
import com.shuishu.blog.common.domain.user.entity.vo.UserInfoVo;
import com.shuishu.blog.common.enums.UserEnum;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：谁书-ss
 * @date ：2022-12-31 13:40
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：Spring Security 工具类
 */
public class SpringSecurityUtils {
    public static final String LOGOUT_URL = "/api/shuishu/blog/auth/logout";
    public static final String LOGIN_URL_LOCAL = "/api/shuishu/blog/auth/local";
    public static final String LOGIN_URL_EMAIL = "/api/shuishu/blog/auth/email";
    public static final String LOGIN_URL_PHONE = "/api/shuishu/blog/auth/phone";

    public static final String LOGIN_USERNAME_KEY = "userAuthIdentifier";
    public static final String LOGIN_USERNAME_FRONT_KEY = "username";
    public static final String LOGIN_PASSWORD_KEY = "userAuthCredential";
    public static final String LOGIN_PASSWORD_FRONT_KEY = "password";
    public static final String LOGIN_CAPTCHA = "captcha";

    /**
     * 系统初始化 角色code
     */
    public static final String DEFAULT_ROLE_CODE = "system-super-role";

    /**
     * 系统初始化 权限code 和 权限url
     * @return 初始化权信息
     */
    public static Map<String, String> getInitPermission() {
        Map<String, String> map = new HashMap<>(4);
        map.put("permission:add", "/api/shuishu/blog/resource/permission/add/**");
        map.put("permission:update", "/api/shuishu/blog/resource/permission/update/**");
        map.put("permission:details", "/api/shuishu/blog/resource/permission/details/**");
        map.put("permission:page", "/api/shuishu/blog/resource/permission/page/**");
        return map;
    }

    public static String getAuthType(String uri) {
        if (StringUtils.hasText(uri)) {
            return switch (uri) {
                case LOGIN_URL_LOCAL -> UserEnum.AuthType.LOCAL.getType();
                case LOGIN_URL_EMAIL -> UserEnum.AuthType.EMAIL.getType();
                case LOGIN_URL_PHONE -> UserEnum.AuthType.PHONE.getType();
                default -> null;
            };
        }
        return null;
    }

    public static String[] ignoreUrlArray() {
        //yml配置文件有访问前缀context-path  SpringSecurity 这里就不能加前缀
        return new String[]{
                "/doc.html",
                "/webjars/**",
                "/v3/api-docs/**",
                "/auth/**",
                //加前缀，是为了过滤器判断使用
                "/api/shuishu/blog/auth/**"
        };
    }

    public static boolean existsInIgnoreUrlArray(String requestUri) {
        for (String ignoreUrl : ignoreUrlArray()) {
            if (requestUri.contains(ignoreUrl.replace("/**", ""))) {
                return true;
            }
        }
        return false;
    }

    public static boolean existsInIgnoreUrlArrayForDb(String requestUri, List<String> isNotAuthorizationUrlList) {
        if (!ObjectUtils.isEmpty(isNotAuthorizationUrlList)) {
            for (String dbIgnoreUrl : isNotAuthorizationUrlList) {
                if (requestUri.contains(dbIgnoreUrl.replace("/**", ""))) {
                    return true;
                }
            }
        }
        return false;
    }


    public static UserInfoVo getUserInfoVo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Object principal = authentication.getPrincipal();
            return (UserInfoVo) principal;
        }
        throw new BusinessException("用户不存在");
    }

}
