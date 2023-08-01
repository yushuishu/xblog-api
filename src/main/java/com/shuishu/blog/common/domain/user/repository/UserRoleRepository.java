package com.shuishu.blog.common.domain.user.repository;


import cn.hutool.log.Log;
import com.shuishu.blog.common.config.jdbc.BaseRepository;
import com.shuishu.blog.common.domain.user.entity.po.UserRole;
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
public interface UserRoleRepository extends BaseRepository<UserRole, Long> {
    /**
     * 查询用户关联的某个角色信息
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @return 用户角色关联信息
     */
    UserRole findUserRoleByUserIdAndRoleId(Long userId, Long roleId);

    /**
     * 查询关联的用户数量
     *
     * @param roleId 角色id
     * @return -
     */
    long countByRoleId(Long roleId);

    /**
     * 删除角色关联的用户信息
     *
     * @param roleId 角色id
     */
    void deleteByRoleId(Long roleId);
}
