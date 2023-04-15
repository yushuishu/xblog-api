package com.shuishu.blog.common.domain.article.repository;


import com.shuishu.blog.common.config.jdbc.BaseRepository;
import com.shuishu.blog.common.domain.article.entity.po.ArticleShare;
import org.springframework.stereotype.Repository;

/**
 * @author ：谁书-ss
 * @date ：2023-04-14 13:00
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：文章分享
 * <p></p>
 */
@Repository
public interface ArticleShareRepository extends BaseRepository<ArticleShare, Long> {
}
