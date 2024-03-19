package com.shuishu.blog.common.domain.user.repository;



import com.shuishu.blog.common.config.jdbc.BaseRepository;
import com.shuishu.blog.common.domain.user.entity.po.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author ：谁书-ss
 * @Date ：2023-01-01 0:21
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：
 */
@Repository
public interface RoleRepository extends BaseRepository<Role, Long> {
    /**
     * 根据角色id集合 查询所有角色
     *
     * @param roleIdList 角色id 集合
     * @return 角色集合
     */
    List<Role> findAllByRoleIdIn(List<Long> roleIdList);

    /**
     * 查询角色
     *
     * @param roleId 角色id
     * @return -
     */
    Role findByRoleId(Long roleId);

    /**
     * 查询角色
     *
     * @param roleCode 角色code
     * @return 角色信息
     */
    Role findRoleByRoleCode(String roleCode);

    /**
     * 获取默认角色
     *
     * @return 默认角色
     */
    @Query(value = "select * from ss_role where default_role=true", nativeQuery = true)
    Role findRoleByDefaultRole();

}
