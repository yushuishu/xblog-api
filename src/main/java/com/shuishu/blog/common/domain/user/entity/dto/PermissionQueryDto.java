package com.shuishu.blog.common.domain.user.entity.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author ：谁书-ss
 * @Date ：2023-04-15 9:29
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：查询权限信息条件
 * <p></p>
 */
@Schema(description = "查询权限信息条件dto")
@Setter
@Getter
@ToString
public class PermissionQueryDto {

    @Schema(description = "权限code")
    private String permissionCode;

    @Schema(description = "权限url")
    private String permissionUrl;

    @Schema(description = "权限描述")
    private String permissionDescription;

    @Schema(description = "权限是否需要授权：true：授权 false：开放")
    private Boolean isNeedAuthorization;

}
