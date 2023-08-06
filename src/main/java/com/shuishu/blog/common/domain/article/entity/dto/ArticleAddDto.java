package com.shuishu.blog.common.domain.article.entity.dto;


import com.shuishu.blog.common.config.base.BaseDTO;
import com.shuishu.blog.common.domain.article.entity.po.Article;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
}
