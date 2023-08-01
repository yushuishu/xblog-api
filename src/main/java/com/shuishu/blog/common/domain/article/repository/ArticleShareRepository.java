package com.shuishu.blog.common.domain.article.repository;


import com.shuishu.blog.common.config.jdbc.BaseRepository;
import com.shuishu.blog.common.domain.article.entity.po.ArticleShare;
import org.springframework.stereotype.Repository;

/**
 * @Author ：谁书-ss
 * @Date ：2023-04-14 13:00
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：文章分享
 * <p></p>
 */
@Repository
public interface ArticleShareRepository extends BaseRepository<ArticleShare, Long> {
}
