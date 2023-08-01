package com.shuishu.blog.common.domain.article.dsl;


import com.shuishu.blog.common.config.jdbc.BaseDsl;
import com.shuishu.blog.common.domain.article.entity.po.QArticleLabel;
import org.springframework.stereotype.Component;

/**
 * @Author ：谁书-ss
 * @Date ：2023-05-02 17:06
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：文章标签
 * <p></p>
 */
@Component
public class ArticleLabelDsl extends BaseDsl {
    private final QArticleLabel qArticleLabel = QArticleLabel.articleLabel;

    public void deleteByLabelId(Long labelId) {
        if (labelId != null) {
            jpaQueryFactory.delete(qArticleLabel).where(qArticleLabel.labelId.eq(labelId)).execute();
        }
    }
}
