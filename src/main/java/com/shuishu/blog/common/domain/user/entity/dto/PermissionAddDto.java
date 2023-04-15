package com.shuishu.blog.common.domain.user.entity.dto;


import io.swagger.v3.oas.annotations.media.Schema;
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
public class PermissionAddDto {

    @Schema(description = "权限code")
    private String permissionCode;

    @Schema(description = "权限url")
    private String permissionUrl;

    @Schema(description = "权限描述")
    private String permissionDescription;

    @Schema(description = "权限是否需要授权：true：授权 false：开放")
    private Boolean isNeedAuthorization;

    @Schema(description = "父级权限id（权限分类）")
    private Long permissionParentId;


}
