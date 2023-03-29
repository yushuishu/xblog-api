package com.shuishu.blog.common.domain.user.repository;


import com.shuishu.blog.common.config.jdbc.BaseRepository;
import com.shuishu.blog.common.domain.user.entity.po.UserAuth;
import org.springframework.stereotype.Repository;

/**
 * @author ：谁书-ss
 * @date ：2023-01-01 15:41
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
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
}
