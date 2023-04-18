package com.shuishu.blog.common.domain.user.entity.vo;


import com.shuishu.blog.common.config.base.BaseVO;
import com.shuishu.blog.common.domain.user.entity.po.Permission;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

/**
 * @author ：谁书-ss
 * @date ：2023-01-02 14:09
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：权限信息vo
 */
@Schema(description = "权限信息vo")
@Setter
@Getter
@ToString
public class PermissionInfoVo extends BaseVO<Permission> {
    @Serial
    private static final long serialVersionUID = 6581320655357167673L;
    /**
     * 权限id
     */
    @Schema(description = "权限id")
    private Long permissionId;

    @Schema(description = "权限名称")
    private String permissionName;
    /**
     * 权限code
     */
    @Schema(description = "权限code")
    private String permissionCode;
    /**
     * 权限url
     */
    @Schema(description = "权限url")
    private String permissionUrl;
    /**
     * 权限描述
     */
    @Schema(description = "权限描述")
    private String permissionDescription;
    /**
     * 权限是否需要授权：true：授权 false：开放
     */
    @Schema(description = "权限是否需要授权：true：授权 false：开放")
    private Boolean isNeedAuthorization;
    /**
     * 父级权限id（权限分类）
     */
    @Schema(description = "父级权限id（权限分类）")
    private Long permissionParentId;
    /**
     * 角色权限关联id
     */
    @Schema(description = "角色权限关联id")
    private Long rolePermissionId;
    /**
     * 角色id
     */
    @Schema(description = "角色id")
    private Long roleId;
}
