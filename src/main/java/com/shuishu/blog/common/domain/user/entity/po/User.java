package com.shuishu.blog.common.domain.user.entity.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.shuishu.blog.common.config.base.BasePO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.util.Date;

/**
 * @Author ：谁书-ss
 * @Date ：2022-12-29 22:38
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：用户基础信息表
 */
@Setter
@Getter
@ToString
@TableName("ss_user")
@Comment(value = "用户表")
public class User extends BasePO {
    @Serial
    private static final long serialVersionUID = 7399511491764319903L;

    @TableId(value = "user_id", type = IdType.ASSIGN_ID)
    @Comment(value = "用户id")
    private Long userId;

    @TableField("nickname")
    @Comment("昵称")
    @Column(nullable = false, unique = true)
    private String nickname;

    @TableField("user_about")
    @Comment("个人简介")
    private String userAbout;

    @TableField("user_photo")
    @Comment("头像")
    @Column(nullable = false)
    private String userPhoto;

    @TableField("user_address")
    @Comment("地址")
    private String userAddress;

    @TableField("industry_id")
    @Comment("行业id")
    private Long industryId;

    @TableField("user_is_account_non_expired")
    @Comment("用户过期true：没有过期  false：过期")
    @Column(nullable = false)
    private Boolean userIsAccountNonExpired;

    @TableField("user_is_account_non_locked")
    @Comment("用户锁定true：没有锁定  false：被锁定")
    @Column(nullable = false)
    private Boolean userIsAccountNonLocked;

    @TableField("user_last_login_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Comment("最后一次登录时间")
    private Date userLastLoginDate;

    @TableField("user_max_login_client_number")
    @Comment("同时登录客户端的人数:最小1最大50（默认1）")
    @Column(nullable = false)
    private Integer userMaxLoginClientNumber;

}
