package com.shuishu.blog.common.domain.article.entity.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.shuishu.blog.common.config.base.BaseDTO;
import com.shuishu.blog.common.domain.article.entity.po.Article;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Author ：谁书-ss
 * @Date ：2023-08-06 22:30
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：添加文章
 * <p></p>
 */
@Schema(description = "添加文章")
@Setter
@Getter
@ToString
public class ArticleAddDto extends BaseDTO<Article> {
    @NotBlank(message = "文章名称不能为空")
    @Schema(description = "文章名称")
    private String articleTitle;

    @NotBlank(message = "文章内容不能为空")
    @Schema(description = "文章Markdown")
    private String articleMarkdown;

    @NotBlank(message = "文章简要说明不能为空")
    @Schema(description = "文章简要说明")
    private String articleBriefDescription;

    @Schema(description = "文章封面图")
    private String articleCoverImage;

    @NotBlank(message = "行业领域不能为空")
    @Schema(description = "涉及行业或领域，以 | 分割")
    private String industryName;

    @Schema(description = "文章标签")
    private List<String> labelNameList;
}
