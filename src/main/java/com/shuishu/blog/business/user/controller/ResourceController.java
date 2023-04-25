package com.shuishu.blog.business.user.controller;


import com.shuishu.blog.business.user.service.ResourceService;
import com.shuishu.blog.common.config.base.ApiResponse;
import com.shuishu.blog.common.config.base.PageDTO;
import com.shuishu.blog.common.config.base.PageVO;
import com.shuishu.blog.common.domain.user.entity.dto.*;
import com.shuishu.blog.common.domain.user.entity.vo.PermissionVo;
import com.shuishu.blog.common.domain.user.entity.vo.RoleVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：谁书-ss
 * @date ：2023-03-10 22:27
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：资源管理：角色、权限
 * <p></p>
 */
@Tag(name = "角色和权限")
@RequiredArgsConstructor
@RestController
@RequestMapping("resource")
public class ResourceController {
    private final ResourceService resourceService;


    @Operation(summary = "添加权限", description = "添加权限")
    @PostMapping("permission/add")
    public ApiResponse<String> addPermission(@Validated @RequestBody PermissionAddDto permissionAddDto) {
        resourceService.addPermission(permissionAddDto);
        return ApiResponse.success();
    }

    @Operation(summary = "更新权限", description = "更新权限")
    @PostMapping("permission/update")
    public ApiResponse<String> updatePermission(@Validated @RequestBody PermissionUpdateDto permissionUpdateDto) {
        resourceService.updatePermission(permissionUpdateDto);
        return ApiResponse.success();
    }

    @Operation(summary = "查询权限详情", description = "查询权限详情")
    @GetMapping("permission/details")
    public ApiResponse<PermissionVo> findPermissionDetails(Long permissionId) {
        return ApiResponse.of(resourceService.findPermissionDetails(permissionId));
    }

    @Operation(summary = "查询权限page", description = "查询权限page")
    @GetMapping("permission/page")
    public ApiResponse<PageVO<PermissionVo>> findPermissionPage(PermissionQueryDto permissionQueryDto, PageDTO pageDTO) {
        return ApiResponse.of(resourceService.findPermissionPage(permissionQueryDto, pageDTO));
    }

    @Operation(summary = "删除权限", description = "删除权限")
    @PostMapping("permission/delete")
    public ApiResponse<String> deletePermission(@RequestParam("permissionId") Long permissionId) {
        resourceService.deletePermission(permissionId);
        return ApiResponse.success();
    }

    @Operation(summary = "添加角色", description = "添加角色")
    @PostMapping("role/add")
    public ApiResponse<String> addRole(@Validated @RequestBody RoleAddDto roleAddDto) {
        resourceService.addRole(roleAddDto);
        return ApiResponse.success();
    }

    @Operation(summary = "更新角色", description = "更新角色")
    @PostMapping("role/update")
    public ApiResponse<String> updateRole(@Validated @RequestBody RoleUpdateDto roleUpdateDto) {
        resourceService.updateRole(roleUpdateDto);
        return ApiResponse.success();
    }

    @Operation(summary = "查询角色详情", description = "查询角色详情")
    @GetMapping("role/details")
    public ApiResponse<RoleVo> findRoleDetails(Long roleId) {
        return ApiResponse.of(resourceService.findRoleDetails(roleId));
    }

    @Operation(summary = "查询角色page", description = "查询角色page")
    @GetMapping("role/page")
    public ApiResponse<PageVO<RoleVo>> findRolePage(RoleQueryDto roleQueryDto, PageDTO pageDTO) {
        return ApiResponse.of(resourceService.findRolePage(roleQueryDto, pageDTO));
    }

    @Operation(summary = "删除角色", description = "删除角色")
    @PostMapping("role/delete")
    public ApiResponse<String> deleteRole(@RequestParam("roleId") Long roleId, @RequestParam(value = "ackDelete", required = false) Boolean ackDelete) {
        resourceService.deleteRole(roleId, ackDelete);
        return ApiResponse.success();
    }

    @Operation(summary = "查询默认角色", description = "查询默认角色")
    @GetMapping("role/default/find")
    public ApiResponse<RoleVo> findDefaultRole() {
        return ApiResponse.of(resourceService.findDefaultRole());
    }

    @Operation(summary = "更新默认角色", description = "更新默认角色")
    @PostMapping("role/default/update")
    public ApiResponse<String> updateDefaultRole(@RequestParam("roleId") Long roleId) {
        resourceService.updateDefaultRole(roleId);
        return ApiResponse.success();
    }

}
