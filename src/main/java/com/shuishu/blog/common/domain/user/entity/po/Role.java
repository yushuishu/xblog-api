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
 * @Date ：2022-12-31 23:49
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：
 */
@Setter
@Getter
@ToString
@TableName("ss_role")
@Comment("角色表")
public class Role extends BasePO {
    @Serial
    private static final long serialVersionUID = -6675981271899865871L;

    @TableId(value = "role_id", type = IdType.ASSIGN_ID)
    @Comment("角色id")
    private Long roleId;

    @TableField("role_name")
    @Comment("角色名称")
    @Column(nullable = false)
    private String roleName;

    @TableField("role_code")
    @Comment("角色code")
    @Column(nullable = false, unique = true)
    private String roleCode;

    @TableField("role_description")
    @Comment("角色描述")
    private String roleDescription;

    @TableField("role_operate_power")
    @Comment("操作权限：true可以；false不可以")
    private Boolean roleOperatePower;

    @TableField("default_role")
    @Comment("普通用户注册，默认角色")
    private Boolean defaultRole;


}
