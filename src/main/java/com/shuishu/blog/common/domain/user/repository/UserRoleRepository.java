package com.shuishu.blog.common.domain.user.repository;


import com.shuishu.blog.common.config.jdbc.BaseRepository;
import com.shuishu.blog.common.domain.user.entity.po.UserRole;
import org.springframework.stereotype.Repository;

/**
 * @author ：谁书-ss
 * @date ：2023-01-01 0:23
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 */
@Repository
public interface UserRoleRepository extends BaseRepository<UserRole, Long> {
}
