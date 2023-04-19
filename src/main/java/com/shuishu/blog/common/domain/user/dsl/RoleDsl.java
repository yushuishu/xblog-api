package com.shuishu.blog.common.domain.user.dsl;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.shuishu.blog.common.config.base.PageDTO;
import com.shuishu.blog.common.config.base.PageVO;
import com.shuishu.blog.common.config.jdbc.BaseDsl;
import com.shuishu.blog.common.domain.user.entity.dto.RoleQueryDto;
import com.shuishu.blog.common.domain.user.entity.po.QRole;
import com.shuishu.blog.common.domain.user.entity.po.QUser;
import com.shuishu.blog.common.domain.user.entity.po.QUserRole;
import com.shuishu.blog.common.domain.user.entity.po.Role;
import com.shuishu.blog.common.domain.user.entity.vo.RoleInfoVo;
import com.shuishu.blog.common.domain.user.entity.vo.RoleVo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

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

    public PageVO<RoleVo> findRolePage(RoleQueryDto roleQueryDto, PageDTO pageDTO) {
        PageVO<RoleVo> page = pageDTO.toPageVO(RoleVo.class);
        BooleanBuilder builder = new BooleanBuilder();
        if (roleQueryDto.getRoleOperatePower() != null) {
            builder.and(qRole.roleOperatePower.eq(roleQueryDto.getRoleOperatePower()));
        }
        if (StringUtils.hasText(roleQueryDto.getKeyword())) {
            builder.and(qRole.roleName.like("%" + roleQueryDto.getKeyword() + "%")
                    .or(qRole.roleCode.like("%" + roleQueryDto.getKeyword() + "%"))
                    .or(qRole.roleDescription.like("%" + roleQueryDto.getKeyword() + "%"))
            );
        }

        List<Long> idList = jpaQueryFactory.select(qRole.roleId).from(qRole).where(builder).fetch();
        if (ObjectUtils.isEmpty(idList)) {
            page.setTotalElements(0);
        }else {
            page.setTotalElements(idList.size());
        }

        List<RoleVo> fetch = jpaQueryFactory.select(Projections.fields(RoleVo.class,
                        qRole.roleId, qRole.roleName,
                        qRole.roleCode, qRole.roleDescription,
                        qRole.createDate, qRole.updateDate,
                        qUser.nickname.as("createNickname"),
                        qUserUpdate.nickname.as("updateNickname")
                ))
                .from(qRole)
                .leftJoin(qUser).on(qRole.createUserId.eq(qUser.userId))
                .leftJoin(qUserUpdate).on(qRole.createUserId.eq(qUserUpdate.userId))
                .where(builder)
                .orderBy(qRole.updateDate.desc())
                .offset(page.getOffset()).limit(page.getPageSize())
                .fetch();

        page.setDataList(fetch);

        return page;
    }
}
