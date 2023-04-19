package com.shuishu.blog.business.user.service;



import com.shuishu.blog.common.config.base.PageDTO;
import com.shuishu.blog.common.config.base.PageVO;
import com.shuishu.blog.common.domain.user.entity.dto.*;
import com.shuishu.blog.common.domain.user.entity.vo.PermissionVo;
import com.shuishu.blog.common.domain.user.entity.vo.RoleVo;

import java.util.List;

/**
 * @author ：谁书-ss
 * @date ：2023-03-10 22:31
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：资源管理：角色、权限
 * <p></p>
 */
public interface ResourceService {
    /**
     * 查询所有权限集合
     *
     * @return 权限集合
     */
    List<PermissionCacheDto> findCachePermissionList();

    /**
     * 添加权限
     *
     * @param permissionAddDto 添加权限信息
     */
    void addPermission(PermissionAddDto permissionAddDto);

    /**
     * 更新权限
     *
     * @param permissionUpdateDto 更新权限信息
     */
    void updatePermission(PermissionUpdateDto permissionUpdateDto);

    /**
     * 查询权限信息详情
     *
     * @param permissionId 权限id
     * @return 权限信息详情
     */
    PermissionVo findPermissionDetails(Long permissionId);

    /**
     * 查询权限page
     *
     * @param permissionQueryDto 查询权限条件
     * @param pageDTO 分页参数
     * @return 分页数据
     */
    PageVO<PermissionVo> findPermissionPage(PermissionQueryDto permissionQueryDto, PageDTO pageDTO);

    /**
     * 删除权限
     *
     * @param permissionId 权限id
     */
    void deletePermission(Long permissionId);

    /**
     * 添加角色
     *
     * @param roleAddDto 添加角色信息
     */
    void addRole(RoleAddDto roleAddDto);

    /**
     * 更新角色
     *
     * @param roleUpdateDto 更新角色信息
     */
    void updateRole(RoleUpdateDto roleUpdateDto);

    /**
     * 查询角色详情
     *
     * @param roleId 角色id
     * @return 角色详情
     */
    RoleVo findRoleDetails(Long roleId);

    /**
     * 查询角色page
     *
     * @param roleQueryDto 查询条件
     * @param pageDTO 分页数据
     * @return 角色信息
     */
    PageVO<RoleVo> findRolePage(RoleQueryDto roleQueryDto, PageDTO pageDTO);

    /**
     * 删除角色
     *
     * @param roleId 角色id
     * @param ackDelete 确认删除
     */
    void deleteRole(Long roleId,  Boolean ackDelete);
}
