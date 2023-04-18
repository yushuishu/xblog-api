package com.shuishu.blog.common.domain.user.dsl;


import com.querydsl.core.types.Projections;
import com.shuishu.blog.common.config.jdbc.BaseDsl;
import com.shuishu.blog.common.domain.user.entity.po.QRole;
import com.shuishu.blog.common.domain.user.entity.po.QUser;
import com.shuishu.blog.common.domain.user.entity.po.QUserRole;
import com.shuishu.blog.common.domain.user.entity.po.Role;
import com.shuishu.blog.common.domain.user.entity.vo.RoleInfoVo;
import com.shuishu.blog.common.domain.user.entity.vo.RoleVo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ：谁书-ss
 * @date ：2023-01-01 0:21
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 */
@Component
public class RoleDsl extends BaseDsl {
    private final QRole qRole = QRole.role;
    private final QUserRole qUserRole = QUserRole.userRole;
    final QUser qUser = QUser.user;
    final QUser qUserUpdate = new QUser("qUserUpdate");

    public List<RoleInfoVo> findRoleInfoByUserId(Long userId) {
        return jpaQueryFactory.select(Projections.fields(RoleInfoVo.class,
                        qRole.roleId,
                        qRole.roleName,
                        qRole.roleCode
                ))
                .from(qUserRole)
                .innerJoin(qRole).on(qUserRole.roleId.eq(qRole.roleId))
                .where(qUserRole.userId.eq(userId)).fetch();
    }

    public Role findNameOrCode(@NotBlank(message = "角色名不能为空") String roleName,
                               @NotBlank(message = "角色code不能为空") String roleCode) {
        return jpaQueryFactory.selectFrom(qRole).where(qRole.roleName.eq(roleName).or(qRole.roleCode.eq(roleCode))).fetchFirst();
    }

    public Role findNameOrCodeAndNeId(@NotBlank(message = "角色名不能为空") String roleName,
                                      @NotBlank(message = "角色code不能为空") String roleCode,
                                      @NotNull(message = "角色id不能为空") Long roleId) {
        return jpaQueryFactory.selectFrom(qRole)
                .where(qRole.roleName.eq(roleName).or(qRole.roleCode.eq(roleCode)).or(qRole.roleId.ne(roleId)))
                .fetchFirst();
    }

    public RoleVo findRoleDetails(Long roleId) {
        return jpaQueryFactory.select(Projections.fields(RoleVo.class,
                qRole.roleId, qRole.roleName,
                qRole.roleCode, qRole.roleDescription,
                qRole.createDate, qRole.updateDate,
                qUser.nickname.as("createNickname"),
                qUserUpdate.nickname.as("updateNickname")
        ))
                .from(qRole)
                .leftJoin(qUser).on(qRole.createUserId.eq(qUser.userId))
                .leftJoin(qUserUpdate).on(qRole.createUserId.eq(qUserUpdate.userId))
                .where(qRole.roleId.eq(roleId)).fetchOne();
    }
}
