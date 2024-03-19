package com.shuishu.blog.common.config.security;


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
 * @Author ：谁书-ss
 * @Date ：2022-12-31 13:40
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：Spring Security 工具类
 */
public class SpringSecurityUtils {
    public static final String LOGOUT_URL = "/api/shuishu/blog/auth/logout";
    public static final String LOGIN_URL_LOCAL = "/api/shuishu/blog/auth/local";
    public static final String LOGIN_URL_EMAIL = "/api/shuishu/blog/auth/email";
    public static final String LOGIN_URL_PHONE = "/api/shuishu/blog/auth/phone";
    public static final String USER_ADD = "/api/shuishu/blog/user/add";

    public static final String LOGIN_USERNAME_KEY = "userAuthIdentifier";
    public static final String LOGIN_USERNAME_FRONT_KEY = "username";
    public static final String LOGIN_PASSWORD_KEY = "userAuthCredential";
    public static final String LOGIN_PASSWORD_FRONT_KEY = "password";
    public static final String LOGIN_CAPTCHA = "captcha";

    /**
     * 系统初始化 角色code
     */
    public static final String DEFAULT_ROLE_SUPER_CODE = "system-super-role";
    public static final String DEFAULT_ROLE_GENERAL_CODE = "system-general-role";

    /**
     * 系统初始化 权限code 和 权限url
     * @return 初始化权信息
     */
    public static Map<String, String> systemAllPermission() {
        Map<String, String> map = new HashMap<>(100);
        // 权限
        map.put("permission:add", "/api/shuishu/blog/resource/permission/add/**");
        map.put("permission:update", "/api/shuishu/blog/resource/permission/update/**");
        map.put("permission:details", "/api/shuishu/blog/resource/permission/details/**");
        map.put("permission:page", "/api/shuishu/blog/resource/permission/page/**");
        map.put("permission:delete", "/api/shuishu/blog/resource/permission/delete/**");
        // 角色
        map.put("role:add", "/api/shuishu/blog/resource/role/add/**");
        map.put("role:update", "/api/shuishu/blog/resource/role/update/**");
        map.put("role:details", "/api/shuishu/blog/resource/role/details/**");
        map.put("role:page", "/api/shuishu/blog/resource/role/page/**");
        map.put("role:delete", "/api/shuishu/blog/resource/role/delete/**");
        map.put("role:default_find", "/api/shuishu/blog/resource/role/default/find/**");
        // 用户
        map.put("user:page", "/api/shuishu/blog/user/page/**");

        // 将普通用户权限加入到当前所有权限中
        map.putAll(systemGeneralPermission());
        return map;
    }

    public static Map<String, String> systemGeneralPermission() {
        Map<String, String> map = new HashMap<>(100);
        // 用户
        map.put("user:update", "/api/shuishu/blog/update/**");
        map.put("user:pwd_update", "/api/shuishu/blog/pwd/update/**");
        map.put("user:forget_pwd_update", "/api/shuishu/blog/forget_pwd/update/**");
        map.put("user:details", "/api/shuishu/blog/details/**");
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
            if (principal != null) {
                UserInfoVo userInfoVo = (UserInfoVo) principal;
                if (userInfoVo.getUserId() == null) {
                    throw new BusinessException("用户不存在");
                }
            }
        }
        throw new BusinessException("用户不存在");
    }

}
