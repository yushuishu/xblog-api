package com.shuishu.blog.common.domain.user.repository;


import com.shuishu.blog.common.config.jdbc.BaseRepository;
import com.shuishu.blog.common.domain.user.entity.po.UserAuth;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author ：谁书-ss
 * @Date ：2023-01-01 15:41
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：
 */
@Repository
public interface UserAuthRepository extends BaseRepository<UserAuth, Long> {
    /**
     * 查询账号信息
     *
     * @param userAuthIdentifier 账号
     * @param userAuthType 类型
     * @return -
     */
    UserAuth findByUserAuthIdentifierAndAndUserAuthType(String userAuthIdentifier, String userAuthType);

    /**
     * 用户信息
     *
     * @param userId 用户id
     * @param userAuthType 用户类型
     * @return 用户信息
     */
    UserAuth findByUserIdAndUserAuthType(Long userId, String userAuthType);

    /**
     * 查询用户授权信息
     *
     * @param userId 用户id
     * @return 用户授权信息
     */
    List<UserAuth> findByUserId(Long userId);
}
