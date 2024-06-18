package com.shuishu.blog.common.domain.article.entity.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shuishu.blog.common.config.base.BasePO;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;

/**
 * @Author ：谁书-ss
 * @Date ：2023-04-10 13:09
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：文章
 * <p></p>
 */
@Setter
@Getter
@ToString
@TableName("ss_article_update_history")
@Comment(value = "文章修改历史表")
public class ArticleUpdateHistory extends BasePO {

    @TableId(value = "article_id", type = IdType.ASSIGN_ID)
    @Comment(value = "文章id")
    private Long articleUpdateHistoryId;

    @TableField(value = "article_id")
    @Comment(value = "文章id")
    private Long articleId;

    @TableField("article_title")
    @Comment("文章名称")
    private String articleTitle;

    @TableField("article_markdown")
    @Comment("文章Markdown")
    @Column()
    private String articleMarkdown;

    @TableField("article_html")
    @Comment("文章HTML")
    private String articleHtml;

    @TableField("article_word_count")
    @Comment("文章字数")
    private Long articleWordCount;

    @TableField("article_brief_description")
    @Comment("文章简要说明")
    private String articleBriefDescription;

    @TableField("article_browse_count")
    @Comment("文章浏览量")
    private Integer articleBrowseCount;

    @TableField("article_upvote")
    @Comment("文章赞点量")
    private Integer articleUpvote;

    @TableField("article_cover_image")
    @Comment("文章封面图")
    private String articleCoverImage;

    @TableField("article_browse_time")
    @Comment("阅读时长")
    private String articleBrowseTime;

    @TableField("industry_name")
    @Comment("涉及行业或领域，以 | 分割")
    private String industryName;

    @TableField("article_variableWeightValue")
    @Comment("文章动态权重值（定时任务更新权重值）")
    private Integer articleVariableWeightValue;

    @TableField("article_publish")
    @Comment("文章发布 true:发布 false:未发布")
    private Boolean articlePublish;

}
