package com.shuishu.blog.common.domain.article.mapper;


/**
 * @author wuZhenFeng
 * @date 2024/4/11 14:08
 */


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shuishu.blog.common.domain.article.entity.po.Article;
import com.shuishu.blog.common.domain.article.entity.vo.ArticleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author ：谁书-ss
 * @Date ：2024/4/11 14:08
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：文章dto
 * <p></p>
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    void updateStatusByArticleId(@Param("articleId") Long articleId, @Param("articleStatus") Integer articleStatus);

    ArticleVo findArticleDetails(@Param("articleId") Long articleId);
}
