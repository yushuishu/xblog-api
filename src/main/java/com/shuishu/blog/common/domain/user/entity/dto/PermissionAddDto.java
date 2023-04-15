package com.shuishu.blog.common.domain.user.entity.dto;


import com.shuishu.blog.common.config.base.BaseDTO;
import com.shuishu.blog.common.domain.user.entity.po.Permission;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ：谁书-ss
 * @date ：2023-04-15 9:05
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：添加权限
 * <p></p>
 */
@Schema(description = "添加权限dto")
@Setter
@Getter
@ToString
public class PermissionAddDto extends BaseDTO<Permission> {

    @NotBlank(message = "权限code不能为空")
    @Schema(description = "权限code")
    private String permissionCode;

    @NotBlank(message = "权限url不能为空")
    @Schema(description = "权限url")
    private String permissionUrl;

    @NotBlank(message = "权限描述不能为空")
    @Schema(description = "权限描述")
    private String permissionDescription;

    @NotNull(message = "权限是否需要授权不能为空")
    @Schema(description = "权限是否需要授权：true：授权 false：开放")
    private Boolean isNeedAuthorization;

    @Schema(description = "父级权限id（权限分类）")
    private Long permissionParentId;

}
