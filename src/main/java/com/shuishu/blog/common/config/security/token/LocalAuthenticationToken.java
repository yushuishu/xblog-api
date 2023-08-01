package com.shuishu.blog.common.config.security.token;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.util.Collection;

/**
 * @Author ：谁书-ss
 * @Date ：2023-03-05 17:42
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：本地登录 token
 * <p></p>
 */
public class LocalAuthenticationToken extends UsernamePasswordAuthenticationToken {
    @Serial
    private static final long serialVersionUID = 8634771078835164272L;

    public LocalAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public LocalAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

}
