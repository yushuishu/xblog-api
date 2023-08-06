package com.shuishu.blog.business.article.service.impl;


import com.shuishu.blog.business.article.service.ArticleService;
import com.shuishu.blog.common.config.base.PageDTO;
import com.shuishu.blog.common.config.base.PageVO;
import com.shuishu.blog.common.domain.article.dsl.ArticleDsl;
import com.shuishu.blog.common.domain.article.entity.dto.ArticleAddDto;
import com.shuishu.blog.common.domain.article.entity.dto.ArticlePublishDto;
import com.shuishu.blog.common.domain.article.entity.dto.ArticleQueryDto;
import com.shuishu.blog.common.domain.article.entity.dto.ArticleUpdateDto;
import com.shuishu.blog.common.domain.article.entity.vo.ArticleVo;
import com.shuishu.blog.common.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author ：谁书-ss
 * @Date ：2023-04-06 20:30
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：文章
 * <p></p>
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleDsl articleDsl;



    @Override
    public void addArticle(ArticleAddDto articleAddDto) {

    }

    @Override
    public void publishArticle(ArticlePublishDto articlePublishDto) {

    }

    @Override
    public PageVO<ArticleVo> findArticlePage(ArticleQueryDto articleQueryDto, PageDTO pageDTO) {
        return null;
    }

    @Override
    public ArticleVo findArticleDetails(Long articleId) {
        return null;
    }

    @Override
    public void updateArticle(ArticleUpdateDto articleUpdateDto) {

    }

    @Override
    public void deleteArticle(Long articleId) {

    }


}
