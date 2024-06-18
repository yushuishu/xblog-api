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
 * @Date ：2022-12-31 23:58
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：
 */
@Setter
@Getter
@ToString
@TableName("ss_user_role")
@Comment("用户角色表")
public class UserRole extends BasePO {
    @Serial
    private static final long serialVersionUID = -1002951400656466356L;

    @TableId(value = "user_role_id", type = IdType.ASSIGN_ID)
    @Comment("用户角色id")
    private Long userRoleId;

    @TableField("user_id")
    @Comment("用户id")
    @Column(nullable = false)
    private Long userId;

    @TableField("role_id")
    @Comment("角色id")
    @Column(nullable = false)
    private Long roleId;
}
