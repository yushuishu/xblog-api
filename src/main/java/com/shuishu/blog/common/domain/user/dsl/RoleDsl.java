package com.shuishu.blog.common.domain.user.dsl;


import com.querydsl.core.types.Projections;
import com.shuishu.blog.common.config.jdbc.BaseDsl;
import com.shuishu.blog.common.domain.user.entity.po.QRole;
import com.shuishu.blog.common.domain.user.entity.po.QUserRole;
import com.shuishu.blog.common.domain.user.entity.vo.RoleInfoVo;
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
}
