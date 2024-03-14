package com.shuishu.blog.common.domain.article.dsl;


import com.shuishu.blog.common.config.base.PageDTO;
import com.shuishu.blog.common.config.base.PageVO;
import com.shuishu.blog.common.config.jdbc.BaseDsl;
import com.shuishu.blog.common.domain.article.entity.dto.ArticleQueryDto;
import com.shuishu.blog.common.domain.article.entity.po.QArticle;
import com.shuishu.blog.common.domain.article.entity.vo.ArticleVo;
import org.springframework.stereotype.Component;

/**
 * @Author ：谁书-ss
 * @Date ：2023-04-14 13:02
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：文章
 * <p></p>
 */
@Component
public class ArticleDsl extends BaseDsl {
    QArticle qArticle = QArticle.article;


    public PageVO<ArticleVo> findArticlePage(ArticleQueryDto articleQueryDto, PageDTO pageDTO) {
        return null;
    }


    public ArticleVo findArticleDetails(Long articleId) {
        if (articleId == null) {
            return null;
        }
        return null;
    }

    public void updateArticleDelete(Long articleId, boolean articleDelete) {
        if (articleId == null) {
            return;
        }
        //jpaQueryFactory.update(qArticle).set(qArticle.articleDelete, articleDelete).where(qArticle.articleId.eq(articleId)).execute();

    }
}
