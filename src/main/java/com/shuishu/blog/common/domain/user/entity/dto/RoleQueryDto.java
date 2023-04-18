package com.shuishu.blog.common.domain.user.entity.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ：谁书-ss
 * @date ：2023-04-18 13:03
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：查询角色条件
 * <p></p>
 */
@Schema(description = "查询角色信息条件dto")
@Setter
@Getter
@ToString
public class RoleQueryDto {
    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "角色code")
    private String roleCode;

    @Schema(description = "角色描述")
    private String roleDescription;

    @Schema(description = "操作编辑删除权限：true可以；false不可以")
    private Boolean roleOperatePower;

}
