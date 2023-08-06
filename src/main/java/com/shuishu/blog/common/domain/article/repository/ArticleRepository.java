package com.shuishu.blog.common.domain.article.repository;


import com.shuishu.blog.common.config.jdbc.BaseRepository;
import com.shuishu.blog.common.domain.article.entity.po.Article;
import org.springframework.stereotype.Repository;

/**
 * @Author ：谁书-ss
 * @Date ：2023-04-14 13:01
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：文章
 * <p></p>
 */
@Repository
public interface ArticleRepository extends BaseRepository<Article, Long> {
    /**
     * 文章id 查询
     *
     * @param articleId 文章id
     * @return 文章
     */
    Article findByArticleId(Long articleId);

}
