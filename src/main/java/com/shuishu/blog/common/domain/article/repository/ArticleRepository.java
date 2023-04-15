package com.shuishu.blog.common.domain.article.repository;


import com.shuishu.blog.common.config.jdbc.BaseRepository;
import com.shuishu.blog.common.domain.article.entity.po.Article;
import org.springframework.stereotype.Repository;

/**
 * @author ：谁书-ss
 * @date ：2023-04-14 13:01
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：文章
 * <p></p>
 */
@Repository
public interface ArticleRepository extends BaseRepository<Article, Long> {
}
