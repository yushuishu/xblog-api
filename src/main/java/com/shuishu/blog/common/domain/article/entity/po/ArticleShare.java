package com.shuishu.blog.common.domain.article.entity.po;


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
 * @author ：谁书-ss
 * @date ：2023-04-14 12:53
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：文章分享
 * <p></p>
 */
@Setter
@Getter
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ss_article_share")
@Comment(value = "文章分享链接表")
public class ArticleShare extends BasePO {

    @Id
    @GeneratedValue(generator = "CustomIdGenerator")
    @GenericGenerator(name = "CustomIdGenerator", strategy = "com.shuishu.blog.common.config.id.CustomIdGenerator")
    @Comment(value = "文章分享id")
    private Long articleShareId;

    @Comment(value = "文章id")
    private Long articleId;

    @Comment(value = "文章分享code")
    private String articleShareCode;

    @Comment(value = "文章分享密码：空就是不需要密码")
    private String articleSharePassWord;

    @Comment(value = "文章分享有效期: 空就是永久")
    private Date articleShareExpirationDate;


}
