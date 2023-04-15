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
    /**
     * 查询用户关联的某个角色信息
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @return 用户角色关联信息
     */
    UserRole findUserRoleByUserIdAndRoleId(Long userId, Long roleId);
}
