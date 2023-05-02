package com.shuishu.blog.common.config.security.service;


import com.shuishu.blog.common.domain.user.entity.vo.UserInfoVo;

/**
 * @author ：谁书-ss
 * @date ：2023-05-02 18:16
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：Security用户service
 * <p></p>
 */
public interface SecurityUserService {
    /**
     * 账号查询
     *
     * @param userAuthIdentifier 账号
     * @param authType 账号类型
     * @return 用户信息
     */
    UserInfoVo findByUserAuthIdentifier(String userAuthIdentifier, String authType);
}
