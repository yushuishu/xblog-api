package com.shuishu.blog.common.domain.user.entity.vo;


import com.shuishu.blog.common.config.base.BaseVO;
import com.shuishu.blog.common.domain.user.entity.po.Role;
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
 * @description ：
 */
@Setter
@Getter
@ToString
public class RoleInfoVo extends BaseVO<Role> {
    @Serial
    private static final long serialVersionUID = 3491624868548321484L;

    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色code
     */
    private String roleCode;

}
