package com.shuishu.blog.common.domain.article.mapper;


/**
 * @author wuZhenFeng
 * @date 2024/4/11 14:09
 */


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shuishu.blog.common.domain.article.entity.po.ArticleLabel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author ：谁书-ss
 * @Date ：2024/4/11 14:09
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：文章标签dto
 * <p></p>
 */
@Mapper
public interface ArticleLabelMapper extends BaseMapper<ArticleLabel> {
}
