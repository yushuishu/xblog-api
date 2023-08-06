package com.shuishu.blog.common.domain.article.entity.dto;


import com.shuishu.blog.common.config.base.BaseDTO;
import com.shuishu.blog.common.domain.article.entity.po.Article;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Author ：谁书-ss
 * @Date ：2023-08-06 22:31
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：更新文章
 * <p></p>
 */
@Schema(description = "更新文章")
@Setter
@Getter
@ToString
public class ArticleUpdateDto extends BaseDTO<Article> {
    @Schema(description = "文章id")
    private Long articleId;

    @Schema(description = "文章名称")
    private String articleTitle;

    @Schema(description = "文章Markdown")
    private String articleMarkdown;

    @Schema(description = "文章简要说明")
    private String articleBriefDescription;

    @Schema(description = "文章封面图")
    private String articleCoverImg;

    @Schema(description = "涉及行业或领域，以 | 分割")
    private String industryName;

    @Schema(description = "文章标签")
    private List<String> labelNameList;

}
