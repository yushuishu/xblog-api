package com.shuishu.blog.business.article.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.shuishu.blog.common.config.base.PageDTO;
import com.shuishu.blog.common.config.base.PageVO;
import com.shuishu.blog.common.domain.article.entity.dto.ArticleAddDto;
import com.shuishu.blog.common.domain.article.entity.dto.ArticleStatusDto;
import com.shuishu.blog.common.domain.article.entity.dto.ArticleQueryDto;
import com.shuishu.blog.common.domain.article.entity.dto.ArticleUpdateDto;
import com.shuishu.blog.common.domain.article.entity.po.Article;
import com.shuishu.blog.common.domain.article.entity.vo.ArticleVo;

/**
 * @Author ：谁书-ss
 * @Date ：2023-04-06 20:29
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：文章
 * <p></p>
 */
public interface ArticleService extends IService<Article> {
    /**
     * 添加文章
     *
     * @param articleAddDto -
     */
    void addArticle(ArticleAddDto articleAddDto);

    /**
     * 发布文章
     *
     * @param articleStatusDto -
     */
    void publishArticle(ArticleStatusDto articleStatusDto);


    /**
     * 分页查询
     *
     * @param articleQueryDto -
     * @param pageDTO -
     * @return -
     */
    PageVO<ArticleVo> findArticlePage(ArticleQueryDto articleQueryDto, PageDTO pageDTO);

    /**
     * 详情
     *
     * @param articleId 文章id
     * @return -
     */
    ArticleVo findArticleDetails(Long articleId);

    /**
     * 更新文章
     *
     * @param articleUpdateDto -
     */
    void updateArticle(ArticleUpdateDto articleUpdateDto);

    /**
     * 删除文章
     *
     * @param articleId -
     */
    void deleteArticle(Long articleId);
}
