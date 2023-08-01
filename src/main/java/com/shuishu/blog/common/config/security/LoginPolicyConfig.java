package com.shuishu.blog.common.config.security;


import com.shuishu.blog.common.enums.UserEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author ：谁书-ss
 * @Date ：2023-03-12 12:49
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：登录策略
 * <p></p>
 */
@Component
public class LoginPolicyConfig {

    @Value("${shuishu.login-policy}")
    private String loginPolicy;


    public String getLoginPolicy() {
        if (UserEnum.LoginPolicy.existLoginPolicy(loginPolicy)) {
            return loginPolicy;
        }
        // 返回默认的登录策略
        return UserEnum.LoginPolicy.ONE.name();
    }

}
