package com.shuishu.blog.common.domain.user.dsl;


import com.shuishu.blog.common.config.jdbc.BaseDsl;
import com.shuishu.blog.common.domain.user.entity.po.QUser;
import com.shuishu.blog.common.domain.user.entity.po.QUserAuth;
import org.springframework.stereotype.Component;

/**
 * @author ：谁书-ss
 * @date ：2023-01-01 0:22
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 */
@Component
public class UserDsl extends BaseDsl {
    private final QUser qUser = QUser.user;
    private final QUserAuth qUserAuth = QUserAuth.userAuth;

}
