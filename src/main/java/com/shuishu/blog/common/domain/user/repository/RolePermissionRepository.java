package com.shuishu.blog.common.domain.user.repository;


import com.shuishu.blog.common.config.jdbc.BaseRepository;
import com.shuishu.blog.common.domain.user.entity.po.RolePermission;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：谁书-ss
 * @date ：2023-01-01 0:22
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 */
@Repository
public interface RolePermissionRepository extends BaseRepository<RolePermission, Long> {
    /**
     * 查询角色权限关联信息
     *
     * @param roleId 角色id
     * @return 角色权限关联信息
     */
    List<RolePermission> findAllByRoleId(Long roleId);
}
