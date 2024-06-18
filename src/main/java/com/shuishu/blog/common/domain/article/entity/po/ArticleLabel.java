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

/**
 * @Author ：谁书-ss
 * @Date ：2023-05-02 17:02
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：文章标签
 * <p></p>
 */
@Setter
@Getter
@ToString
@TableName("ss_article_label")
@Comment(value = "文章标签表")
public class ArticleLabel extends BasePO {

    @TableId(value = "article_label_id", type = IdType.ASSIGN_ID)
    @Comment(value = "文章标签id")
    private Long articleLabelId;

    @TableField("article_id")
    @Comment("文章id")
    @Column(nullable = false, unique = true)
    private Long articleId;

    @TableField("label_id")
    @Comment("标签id")
    @Column(nullable = false, unique = true)
    private Long labelId;

}
