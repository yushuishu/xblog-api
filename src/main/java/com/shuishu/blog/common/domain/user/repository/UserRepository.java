package com.shuishu.blog.common.domain.user.repository;


import com.shuishu.blog.common.config.jdbc.BaseRepository;
import com.shuishu.blog.common.domain.user.entity.po.User;
import org.springframework.stereotype.Repository;

/**
 * @Author ：谁书-ss
 * @Date ：2023-01-01 0:23
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：
 */
@Repository
public interface UserRepository extends BaseRepository<User, Long> {
    /**
     * 用户信息
     *
     * @param userId 用户id
     * @return 用户信息
     */
    User findByUserId(Long userId);

    /**
     * 用户信息
     *
     * @param nickname 昵称
     * @return 用户信息
     */
    User findByNickname(String nickname);

}
