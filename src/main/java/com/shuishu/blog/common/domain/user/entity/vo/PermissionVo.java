package com.shuishu.blog.common.domain.user.entity.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author ：谁书-ss
 * @Date ：2023-04-15 9:21
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：权限信息vo
 * <p></p>
 */
@Schema(description = "权限信息vo")
@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PermissionVo {

    @Schema(description = "权限id")
    private Long permissionId;

    @Schema(description = "权限名称")
    private String permissionName;

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

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @Schema(description = "修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    @Schema(description = "创建人昵称")
    private String createNickname;

    @Schema(description = "更新人昵称")
    private String updateNickname;

    @Schema(description = "父级权限描述")
    private String parentPermissionDescription;

}
