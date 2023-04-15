package com.shuishu.blog.common.domain.user.entity.vo;


import com.shuishu.blog.common.config.base.BaseVO;
import com.shuishu.blog.common.domain.user.entity.po.Role;
import io.swagger.v3.oas.annotations.media.Schema;
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
 * @description ：角色信息vo
 */
@Schema(description = "角色信息vo")
@Setter
@Getter
@ToString
public class RoleInfoVo extends BaseVO<Role> {
    @Serial
    private static final long serialVersionUID = 3491624868548321484L;

    /**
     * 角色id
     */
    @Schema(description = "")
    private Long roleId;
    /**
     * 角色名称
     */
    @Schema(description = "")
    private String roleName;
    /**
     * 角色code
     */
    @Schema(description = "")
    private String roleCode;

}
