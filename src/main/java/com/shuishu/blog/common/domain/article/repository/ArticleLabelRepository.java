package com.shuishu.blog.common.domain.article.repository;


import com.shuishu.blog.common.config.jdbc.BaseRepository;
import com.shuishu.blog.common.domain.article.entity.po.ArticleLabel;
import org.springframework.stereotype.Repository;

/**
 * @Author ：谁书-ss
 * @Date ：2023-05-02 17:05
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：文章标签
 * <p></p>
 */
@Repository
public interface ArticleLabelRepository extends BaseRepository<ArticleLabel, Long> {


}
