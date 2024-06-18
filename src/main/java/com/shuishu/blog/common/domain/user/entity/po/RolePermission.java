package com.shuishu.blog.common.domain.user.entity.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shuishu.blog.common.config.base.BasePO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;

/**
 * @Author ：谁书-ss
 * @Date ：2023-01-01 0:08
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：
 */
@Setter
@Getter
@ToString
@TableName("ss_role_permission")
@Comment("角色权限关联表")
public class RolePermission extends BasePO {
    @Serial
    private static final long serialVersionUID = 4243243150263214388L;

    @TableId(value = "role_permission_id", type = IdType.ASSIGN_ID)
    @Comment("角色权限关联id")
    private Long rolePermissionId;

    @TableField("role_id")
    @Comment("角色id")
    @Column(nullable = false)
    private Long roleId;

    @TableField("permission_id")
    @Comment("权限id")
    @Column(nullable = false)
    private Long permissionId;
}
