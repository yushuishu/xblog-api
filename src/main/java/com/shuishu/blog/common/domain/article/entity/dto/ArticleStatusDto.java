package com.shuishu.blog.common.domain.article.entity.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author ：谁书-ss
 * @Date ：2023-08-06 22:34
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：发布/下架 文章
 * <p></p>
 */
@Schema(description = "发布/下架 文章")
@Setter
@Getter
@ToString
public class ArticleStatusDto {
    @Schema(description = "文章id")
    private Long articleId;

    @Schema(description = "状态：0:待编辑 1:未发布 2:已发布 3:已删除")
    private Integer articleStatus;

}
