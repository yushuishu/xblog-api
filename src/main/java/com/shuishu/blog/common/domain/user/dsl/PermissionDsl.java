package com.shuishu.blog.common.domain.user.dsl;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.shuishu.blog.common.config.base.PageDTO;
import com.shuishu.blog.common.config.base.PageVO;
import com.shuishu.blog.common.config.jdbc.BaseDsl;
import com.shuishu.blog.common.domain.user.entity.dto.PermissionCacheDto;
import com.shuishu.blog.common.domain.user.entity.dto.PermissionQueryDto;
import com.shuishu.blog.common.domain.user.entity.dto.RoleCacheDto;
import com.shuishu.blog.common.domain.user.entity.po.*;
import com.shuishu.blog.common.domain.user.entity.vo.PermissionInfoVo;
import com.shuishu.blog.common.domain.user.entity.vo.PermissionVo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author ：谁书-ss
 * @date ：2023-01-01 0:19
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 */
@Component
public class PermissionDsl extends BaseDsl {
    final QPermission qPermission = QPermission.permission;
    final QRolePermission qRolePermission = QRolePermission.rolePermission;
    final QRole qRole = QRole.role;
    final QPermission qPermissionParent = new QPermission("qPermissionParent");
    final QUser qUser = QUser.user;
    final QUser qUserUpdate = new QUser("qUserUpdate");

    public List<PermissionInfoVo> findPermissionInfoByRoleIdList(List<Long> roleIdList) {
        return jpaQueryFactory.select(Projections.fields(PermissionInfoVo.class,
                        qPermission.permissionId,
                        qPermission.permissionCode,
                        qPermission.permissionDescription,
                        qPermission.permissionUrl,
                        qPermission.permissionParentId,
                        qRolePermission.roleId,
                        qRolePermission.rolePermissionId
                ))
                .from(qRolePermission)
                .leftJoin(qPermission).on(qRolePermission.permissionId.eq(qPermission.permissionId))
                .where(qRolePermission.roleId.in(roleIdList))
                .fetch();
    }


    public List<PermissionCacheDto> findCachePermissionList() {
        return jpaQueryFactory.select(Projections.fields(PermissionCacheDto.class,
                        qRole.roleCode,
                        qPermission.permissionCode,
                        qPermission.permissionUrl,
                        qPermission.isNeedAuthorization
                ))
                .from(qPermission)
                .leftJoin(qRolePermission).on(qPermission.permissionId.eq(qRolePermission.permissionId))
                .leftJoin(qRole).on(qRole.roleId.eq(qRolePermission.roleId))
                .transform(GroupBy.groupBy(qPermission).list((
                                Projections.fields(PermissionCacheDto.class,
                                        qPermission.permissionUrl,
                                        qPermission.permissionCode,
                                        qPermission.isNeedAuthorization,
                                        GroupBy.list(Projections.fields(RoleCacheDto.class,
                                                qRole.roleCode
                                        )).as("roleCacheDtoList"))
                        )
                ));
    }

    public Permission findByNameOrCodeOrUrl(String permissionName, String permissionCode, String permissionUrl) {
        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.hasText(permissionName)) {
            builder.or(qPermission.permissionName.eq(permissionName));
        }
        if (StringUtils.hasText(permissionCode)) {
            builder.or(qPermission.permissionCode.eq(permissionCode));
        }
        if (StringUtils.hasText(permissionUrl)) {
            builder.or(qPermission.permissionUrl.eq(permissionUrl));
        }
        return jpaQueryFactory.selectFrom(qPermission).where(builder).fetchFirst();
    }

    public PermissionVo findPermissionDetails(Long permissionId) {
        if (permissionId == null) {
            return null;
        }
        return jpaQueryFactory.select(Projections.fields(
                        PermissionVo.class,
                        qPermission.permissionId,
                        qPermission.permissionName,
                        qPermission.permissionCode,
                        qPermission.permissionDescription,
                        qPermission.isNeedAuthorization,
                        qPermission.permissionId,
                        qPermission.createDate,
                        qPermission.updateDate,
                        qPermissionParent.permissionDescription.as("parentPermissionDescription"),
                        qUser.nickname.as("createNickname"),
                        qUserUpdate.nickname.as("updateNickname")
                ))
                .from(qPermission)
                .leftJoin(qPermissionParent).on(qPermission.permissionParentId.eq(qPermissionParent.permissionId))
                .leftJoin(qUser).on(qPermission.createUserId.eq(qUser.createUserId))
                .leftJoin(qUserUpdate).on(qPermission.updateUserId.eq(qUserUpdate.userId))
                .where(qPermission.permissionId.eq(permissionId))
                .fetchOne();
    }

    public Permission findByNameOrCodeOrUrlAndNeId(@NotBlank(message = "权限名不能为空") String permissionName,
                                                   @NotBlank(message = "权限code不能为空") String permissionCode,
                                                   @NotBlank(message = "权限url不能为空") String permissionUrl,
                                                   @NotNull(message = "权限id不能为空") Long permissionId) {
        return jpaQueryFactory.selectFrom(qPermission)
                .where(qPermission.permissionName.eq(permissionName)
                        .or(qPermission.permissionCode.eq(permissionCode)
                                .or(qPermission.permissionUrl.eq(permissionUrl)))
                        .and(qPermission.permissionId.ne(permissionId)))
                .fetchFirst();
    }

    public PageVO<PermissionVo> findPermissionPage(PermissionQueryDto permissionQueryDto, PageDTO pageDTO) {
        PageVO<PermissionVo> page = pageDTO.toPageVO(PermissionVo.class);
        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.hasText(permissionQueryDto.getPermissionCode())) {
            builder.and(qPermission.permissionCode.like("%" + permissionQueryDto.getPermissionCode() + "%"));
        }
        if (StringUtils.hasText(permissionQueryDto.getPermissionUrl())) {
            builder.and(qPermission.permissionUrl.like("%" + permissionQueryDto.getPermissionUrl() + "%"));
        }
        if (StringUtils.hasText(permissionQueryDto.getPermissionDescription())) {
            builder.and(qPermission.permissionDescription.like("%" + permissionQueryDto.getPermissionDescription() + "%"));
        }
        if (permissionQueryDto.getIsNeedAuthorization() != null) {
            builder.and(qPermission.isNeedAuthorization.eq(permissionQueryDto.getIsNeedAuthorization()));
        }

        List<Long> idList = jpaQueryFactory.select(qPermission.permissionId).from(qPermission).where(builder).fetch();
        if (ObjectUtils.isEmpty(idList)) {
            page.setTotalElements(0);
        } else {
            page.setTotalElements(idList.size());
        }

        List<PermissionVo> fetch = jpaQueryFactory.select(Projections.fields(
                        PermissionVo.class,
                        qPermission.permissionId,
                        qPermission.permissionName,
                        qPermission.permissionCode,
                        qPermission.permissionDescription,
                        qPermission.isNeedAuthorization,
                        qPermission.permissionId,
                        qPermission.createDate,
                        qPermission.updateDate,
                        qPermissionParent.permissionDescription.as("parentPermissionDescription"),
                        qUser.nickname.as("createNickname"),
                        qUserUpdate.nickname.as("updateNickname")
                ))
                .from(qPermission)
                .leftJoin(qPermissionParent).on(qPermission.permissionParentId.eq(qPermissionParent.permissionId))
                .leftJoin(qUser).on(qPermission.createUserId.eq(qUser.createUserId))
                .leftJoin(qUserUpdate).on(qPermission.updateUserId.eq(qUserUpdate.userId))
                .where(builder)
                .orderBy(qPermission.updateDate.desc())
                .offset(page.getOffset()).limit(page.getPageSize())
                .fetch();

        page.setDataList(fetch);

        return page;
    }
}
