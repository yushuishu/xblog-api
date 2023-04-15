package com.shuishu.blog.common.domain.user.dsl;


import com.querydsl.core.BooleanBuilder;
import com.shuishu.blog.common.config.jdbc.BaseDsl;
import com.shuishu.blog.common.domain.user.entity.po.QRolePermission;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author ：谁书-ss
 * @date ：2023-01-01 0:22
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 */
@Component
public class RolePermissionDsl extends BaseDsl {
    private final QRolePermission qRolePermission = QRolePermission.rolePermission;


    public List<Long> findPermissionIdsByRoleIdAndPermissionIds(Long roleId, List<Long> permissionIdList) {
        BooleanBuilder builder = new BooleanBuilder();
        if (roleId != null) {
            builder.and(qRolePermission.roleId.eq(roleId));
        }
        if (!ObjectUtils.isEmpty(permissionIdList)) {
            builder.and(qRolePermission.permissionId.in(permissionIdList));
        }
        return jpaQueryFactory.select(qRolePermission.permissionId).from(qRolePermission).where(builder).fetch();
    }
}
