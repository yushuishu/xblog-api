package com.shuishu.blog.business.user.controller;


import com.shuishu.blog.business.user.service.ResourceService;
import com.shuishu.blog.common.config.base.ApiResponse;
import com.shuishu.blog.common.config.base.PageDTO;
import com.shuishu.blog.common.domain.user.entity.dto.PermissionAddDto;
import com.shuishu.blog.common.domain.user.entity.dto.PermissionQueryDto;
import com.shuishu.blog.common.domain.user.entity.dto.PermissionUpdateDto;
import com.shuishu.blog.common.domain.user.entity.vo.PermissionVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：谁书-ss
 * @date ：2023-03-10 22:27
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：资源管理：角色、权限
 * <p></p>
 */
@Tag(name = "角色权限")
@RequiredArgsConstructor
@RestController
@RequestMapping("resource")
public class ResourceController {
    private final ResourceService resourceService;


    @Operation(summary = "添加权限", description = "添加权限")
    @PostMapping("permission/add")
    public ApiResponse<String> addPermission(PermissionAddDto permissionAddDto) {
        resourceService.addPermission(permissionAddDto);
        return ApiResponse.success();
    }

    @Operation(summary = "更新权限", description = "更新权限")
    @PostMapping("permission/update")
    public ApiResponse<String> updatePermission(PermissionUpdateDto permissionUpdateDto) {
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
    public ApiResponse<Page<PermissionVo>> findPermissionPage(PermissionQueryDto permissionQueryDto, PageDTO pageDTO) {
        return ApiResponse.of(resourceService.findPermissionPage(permissionQueryDto, pageDTO));
    }

}
