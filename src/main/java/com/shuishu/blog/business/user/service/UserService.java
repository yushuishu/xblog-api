package com.shuishu.blog.business.user.service;


import com.shuishu.blog.common.domain.user.entity.vo.UserInfoVo;

/**
 * @author ：谁书-ss
 * @date ：2022-12-29 22:39
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 */
public interface UserService {
    /**
     * 账号查询
     *
     * @param userAuthIdentifier 账号
     * @param authType 账号类型
     * @return 用户信息
     */
    UserInfoVo findByUserAuthIdentifier(String userAuthIdentifier, String authType);

}
