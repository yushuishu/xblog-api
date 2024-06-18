package com.shuishu.blog.common.domain.article.entity.vo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.shuishu.blog.common.config.base.BaseVO;
import com.shuishu.blog.common.domain.article.entity.po.Article;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Author ：谁书-ss
 * @Date ：2023-04-10 13:09
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：文章
 * <p></p>
 */
@Schema(description = "文章vo")
@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ArticleVo extends BaseVO<Article> {

    @Schema(description = "文章id")
    private Long articleId;

    @Schema(description = "文章名称")
    private String articleTitle;

    @Schema(description = "文章Markdown")
    private String articleMarkdown;

    @Schema(description = "文章HTML")
    private String articleHtml;

    @Schema(description = "文章字数（减少查询）")
    private Long articleWordCount;

    @Schema(description = "文章简要说明")
    private String articleBriefDescription;

    @Schema(description = "文章浏览量")
    private Integer articleBrowseCount;

    @Schema(description = "文章赞点量")
    private Integer articleUpvote;

    @Schema(description = "文章封面图")
    private String articleCoverImage;

    @Schema(description = "阅读时长（分钟）")
    private String articleBrowseTime;

    @Schema(description = "涉及行业或领域，以 | 分割")
    private String industryName;

    @Schema(description = "文章动态权重值（定时任务更新权重值）")
    private Integer articleVariableWeightValue;

    @Schema(description = "状态：0:待编辑 1:未发布 2:已发布 3:已删除")
    private Integer articleStatus;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @Schema(description = "修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    @Schema(description = "创建人昵称")
    private String createNickname;

    @Schema(description = "更新人昵称")
    private String updateNickname;

    @Schema(description = "文章标签")
    private List<String> labelNameList;

}
