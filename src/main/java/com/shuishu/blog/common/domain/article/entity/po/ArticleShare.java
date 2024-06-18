package com.shuishu.blog.common.domain.article.entity.po;


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

import java.util.Date;

/**
 * @Author ：谁书-ss
 * @Date ：2023-04-14 12:53
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：文章分享
 * <p></p>
 */
@Setter
@Getter
@ToString
@TableName("ss_article_share")
@Comment(value = "文章分享链接表")
public class ArticleShare extends BasePO {

    @TableId(value = "article_share_id", type = IdType.ASSIGN_ID)
    @Comment(value = "文章分享id")
    private Long articleShareId;

    @TableField("article_id")
    @Comment(value = "文章id")
    private Long articleId;

    @TableField("article_share_code")
    @Comment(value = "文章分享code")
    private String articleShareCode;

    @TableField("article_share_password")
    @Comment(value = "文章分享密码：空就是不需要密码")
    private String articleSharePassword;

    @TableField("article_share_expiration_date")
    @Comment(value = "文章分享有效期: 空就是永久")
    private Date articleShareExpirationDate;

    @TableField("article_share_expired")
    @Comment(value = "是否过期，承接有过期时间的链接，业务上通过此属性判断")
    private Boolean articleShareExpired;
}
