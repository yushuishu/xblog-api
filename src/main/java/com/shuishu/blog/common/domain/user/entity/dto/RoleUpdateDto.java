package com.shuishu.blog.common.domain.user.entity.dto;


import com.shuishu.blog.common.config.base.BaseDTO;
import com.shuishu.blog.common.domain.user.entity.po.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ：谁书-ss
 * @date ：2023-04-18 12:59
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：角色更新dto
 * <p></p>
 */
@Schema(description = "角色更新dto")
@Setter
@Getter
@ToString
public class RoleUpdateDto extends BaseDTO<Role> {
    @NotNull(message = "角色id不能为空")
    @Schema(description = "角色id")
    private Long roleId;

    @NotBlank(message = "角色名称不能为空")
    @Schema(description = "角色名称")
    private String roleName;

    @NotBlank(message = "角色code不能为空")
    @Schema(description = "角色code")
    private String roleCode;

    @NotBlank(message = "角色描述不能为空")
    @Schema(description = "角色描述")
    private String roleDescription;

    @Schema(description = "操作编辑删除权限：true可以；false不可以")
    private Boolean roleOperatePower;
}
