package com.shuishu.blog.common.domain.article.entity.po;


import com.shuishu.blog.common.config.base.BasePO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author ：谁书-ss
 * @date ：2023-04-10 13:09
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：文章
 * <p></p>
 */
@Setter
@Getter
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ss_article")
@Comment(value = "文章表")
public class Article extends BasePO {
    @Id
    @GeneratedValue(generator = "CustomIdGenerator")
    @GenericGenerator(name = "CustomIdGenerator", strategy = "com.shuishu.blog.common.config.id.CustomIdGenerator")
    @Comment(value = "文章id")
    private Long articleId;

    @Comment("文章名称")
    @Column(nullable = false, unique = true)
    private String articleTitle;




}
