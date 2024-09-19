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
@TableName("ss_permission")
@Comment("权限表")
public class Permission extends BasePO {
    @Serial
    private static final long serialVersionUID = -2977268131213897533L;

    @TableId(value = "permission_id", type = IdType.ASSIGN_ID)
    @Comment("权限id")
    private Long permissionId;

    @TableField("permission_name")
    @Comment("权限名称")
    @Column(nullable = false)
    private String permissionName;

    @TableField("permission_code")
    @Comment("权限code")
    @Column(nullable = false, unique = true)
    private String permissionCode;

    @TableField("permission_url")
    @Comment("权限url")
    @Column(nullable = false, unique = true)
    private String permissionUrl;

    @TableField("permission_description")
    @Comment("权限描述")
    @Column(nullable = false)
    private String permissionDescription;

    @TableField("is_need_authorization")
    @Comment("权限是否需要授权：true：授权 false：开放")
    @Column(nullable = false)
    private Boolean isNeedAuthorization;

    @TableField("permission_operate_power")
    @Comment("操作权限：true允许；false不允许")
    private Boolean permissionOperatePower;

    @TableField("permission_parent_id")
    @Comment("父级权限id（权限分类）")
    private Long permissionParentId;

}
