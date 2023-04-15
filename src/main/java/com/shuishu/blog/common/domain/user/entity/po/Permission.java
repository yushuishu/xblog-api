package com.shuishu.blog.common.domain.user.entity.po;


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
 * @author ：谁书-ss
 * @date ：2023-01-01 0:08
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 */
@Setter
@Getter
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ss_permission")
@Comment("权限表")
public class Permission extends BasePO {
    @Serial
    private static final long serialVersionUID = -2977268131213897533L;

    @Id
    @GeneratedValue(generator = "CustomIdGenerator")
    @GenericGenerator(name = "CustomIdGenerator", strategy = "com.shuishu.blog.common.config.id.CustomIdGenerator")
    @Comment("权限id")
    private Long permissionId;

    @Comment("权限code")
    @Column(nullable = false, unique = true)
    private String permissionCode;

    @Comment("权限url")
    @Column(nullable = false, unique = true)
    private String permissionUrl;

    @Comment("权限描述")
    @Column(nullable = false)
    private String permissionDescription;

    @Comment("权限是否需要授权：true：授权 false：开放")
    @Column(nullable = false)
    private Boolean isNeedAuthorization;

    @Comment("操作编辑删除权限：true可以；false不可以")
    private Boolean permissionOperatePower;

    @Comment("父级权限id（权限分类）")
    private Long permissionParentId;

}
