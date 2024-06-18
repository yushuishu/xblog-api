package com.shuishu.blog.common.domain.user.entity.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shuishu.blog.common.config.base.BasePO;
import com.shuishu.blog.common.enums.UserEnum;
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
 * @Date ：2023-01-01 14:49
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：用户授权信息表
 * , uniqueConstraints = @UniqueConstraint(columnNames = {"userId", "userAuthType"}) 联合唯一键设置问题
 */
@Setter
@Getter
@ToString
@TableName("ss_user_auth")
@Comment("用户授权表")
public class UserAuth extends BasePO {
    @Serial
    private static final long serialVersionUID = 5511638296466304494L;

    @TableId(value = "user_auth_id", type = IdType.ASSIGN_ID)
    @Comment("用户授权id")
    private Long userAuthId;

    @TableField("user_id")
    @Comment("用户id")
    @Column(nullable = false)
    private Long userId;

    /**
     * 授权类型
     * {@link UserEnum.AuthType}
     */
    @TableField("user_auth_type")
    @Comment("授权类型")
    @Column(nullable = false)
    private String userAuthType;

    @TableField("user_auth_identifier")
    @Comment("登录号：唯一识别码")
    @Column(nullable = false, unique = true)
    private String userAuthIdentifier;

    @TableField("user_auth_credential")
    @Comment("凭证信息")
    private String userAuthCredential;

    @TableField("user_auth_refresh_token")
    @Comment("刷新token（不是本地记住我，是OAuth第三方的刷新token）")
    private String userAuthRefreshToken;

    @TableField("user_auth_nickname")
    @Comment("授权昵称")
    private String userAuthNickname;

    @TableField("user_auth_photo")
    @Comment("授权头像")
    private String userAuthPhoto;

}
