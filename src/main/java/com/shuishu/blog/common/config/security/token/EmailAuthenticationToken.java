package com.shuishu.blog.common.config.security.token;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.util.Collection;

/**
 * @Author ：谁书-ss
 * @Date ：2023-03-05 14:33
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：邮箱 token
 * <p></p>
 */
public class EmailAuthenticationToken extends UsernamePasswordAuthenticationToken {
    @Serial
    private static final long serialVersionUID = 3877790608887810253L;


    public EmailAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public EmailAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(principal, "", authorities);
    }

}
