package com.shuishu.blog.business.article.controller;


import com.shuishu.blog.business.article.service.ArticleService;
import com.shuishu.blog.common.config.base.ApiResponse;
import com.shuishu.blog.common.config.base.PageDTO;
import com.shuishu.blog.common.config.base.PageVO;
import com.shuishu.blog.common.domain.article.entity.dto.ArticleAddDto;
import com.shuishu.blog.common.domain.article.entity.dto.ArticleQueryDto;
import com.shuishu.blog.common.domain.article.entity.dto.ArticlePublishDto;
import com.shuishu.blog.common.domain.article.entity.dto.ArticleUpdateDto;
import com.shuishu.blog.common.domain.article.entity.vo.ArticleVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author ：谁书-ss
 * @Date ：2023-04-06 20:29
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：文章
 * <p></p>
 */
@Tag(name = "文章")
@RequiredArgsConstructor
@RestController
@RequestMapping("article")
public class ArticleController {
    private final ArticleService articleService;



    @Operation(summary = "添加", description = "添加文章")
    @PostMapping("add")
    public ApiResponse<String> addArticle(@RequestBody @Validated ArticleAddDto articleAddDto) {
        articleService.addArticle(articleAddDto);
        return ApiResponse.success();
    }

    @Operation(summary = "发布/取消", description = "发布/取消文章")
    @PostMapping("publish/un_publish")
    public ApiResponse<String> publishArticle(ArticlePublishDto articlePublishDto) {
        articleService.publishArticle(articlePublishDto);
        return ApiResponse.success();
    }

    @Operation(summary = "分页", description = "分页查询文章")
    @GetMapping("page")
    public ApiResponse<PageVO<ArticleVo>> findArticlePage(ArticleQueryDto articleQueryDto, PageDTO pageDTO) {
        return ApiResponse.of(articleService.findArticlePage(articleQueryDto, pageDTO));
    }

    @Operation(summary = "详情", description = "查询文章详情")
    @GetMapping("details")
    public ApiResponse<ArticleVo> findArticleDetails(Long articleId) {
        return ApiResponse.of(articleService.findArticleDetails(articleId));
    }

    @Operation(summary = "更新", description = "更新文章")
    @PostMapping("update")
    public ApiResponse<String> updateArticle(@RequestBody @Validated ArticleUpdateDto articleUpdateDto) {
        articleService.updateArticle(articleUpdateDto);
        return ApiResponse.success();
    }

    @Operation(summary = "删除", description = "删除文章")
    @PostMapping("delete")
    public ApiResponse<String> deleteArticle(Long articleId) {
        articleService.deleteArticle(articleId);
        return ApiResponse.success();
    }


}
