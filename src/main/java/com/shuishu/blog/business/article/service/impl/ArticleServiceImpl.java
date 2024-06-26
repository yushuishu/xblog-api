package com.shuishu.blog.business.article.service.impl;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuishu.blog.business.article.service.ArticleService;
import com.shuishu.blog.common.config.base.PageDTO;
import com.shuishu.blog.common.config.base.PageVO;
import com.shuishu.blog.common.config.exception.BusinessException;
import com.shuishu.blog.common.config.security.SpringSecurityUtils;
import com.shuishu.blog.common.domain.article.dsl.ArticleDsl;
import com.shuishu.blog.common.domain.article.entity.dto.ArticleAddDto;
import com.shuishu.blog.common.domain.article.entity.dto.ArticleStatusDto;
import com.shuishu.blog.common.domain.article.entity.dto.ArticleQueryDto;
import com.shuishu.blog.common.domain.article.entity.dto.ArticleUpdateDto;
import com.shuishu.blog.common.domain.article.entity.po.Article;
import com.shuishu.blog.common.domain.article.entity.vo.ArticleVo;
import com.shuishu.blog.common.domain.article.mapper.ArticleMapper;
import com.shuishu.blog.common.domain.article.repository.ArticleRepository;
import com.shuishu.blog.common.domain.refuse.entity.po.Refuse;
import com.shuishu.blog.common.domain.refuse.mapper.RefuseMapper;
import com.shuishu.blog.common.domain.refuse.repository.RefuseRepository;
import com.shuishu.blog.common.domain.user.entity.vo.UserInfoVo;
import com.shuishu.blog.common.enums.ArticleEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.Objects;

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
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    private final ArticleMapper articleMapper;
    private RefuseMapper refuseMapper;


    @Override
    public void addArticle(ArticleAddDto articleAddDto) {
        if (ObjectUtils.isEmpty(articleAddDto.getLabelNameList())) {
            throw new BusinessException("文章标签不能为空");
        }
        if (articleAddDto.getArticleTitle().length() > 50) {
            throw new BusinessException("文章标题长度不能超过50个字符");
        }
        if (articleAddDto.getArticleMarkdown().length() > 10000000) {
            throw new BusinessException("文章内容不能超过1000万个字符");
        }
        if (articleAddDto.getArticleBriefDescription().length() < 30 || articleAddDto.getArticleBriefDescription().length() > 300) {
            throw new BusinessException("文章简要说明要求大于20个字符，不超过300个字符");
        }

        Article article = articleAddDto.toPo(Article.class);
        // 获取当前用户
        UserInfoVo userInfoVo = SpringSecurityUtils.getUserInfoVo();
        article.setCreateUserId(userInfoVo.getUserId());
        article.setUpdateUserId(userInfoVo.getUserId());
        save(article);
    }

    @Override
    public void publishArticle(ArticleStatusDto articleStatusDto) {
        Article article = getById(articleStatusDto.getArticleId());
        Objects.requireNonNull(article, "文章不存在");
        ArticleEnum.Status.verify(articleStatusDto.getArticleStatus());
        articleMapper.updateStatusByArticleId(articleStatusDto.getArticleId(), articleStatusDto.getArticleStatus());
    }

    @Override
    public PageVO<ArticleVo> findArticlePage(ArticleQueryDto articleQueryDto, PageDTO pageDTO) {

        articleMapper.findArticlePage(articleQueryDto, pageDTO);
        return articleDsl.findArticlePage(articleQueryDto, pageDTO);
    }

    @Override
    public ArticleVo findArticleDetails(Long articleId) {
        return articleMapper.findArticleDetails(articleId);
    }

    @Override
    public void updateArticle(ArticleUpdateDto articleUpdateDto) {

    }

    @Override
    public void deleteArticle(Long articleId) {
        Article article = articleRepository.findByArticleId(articleId);
        Objects.requireNonNull(article, "文章不存在");
        if (Boolean.TRUE.equals(article.getArticleDelete())) {
            throw new BusinessException("文章已删除");
        }
        articleDsl.updateArticleDelete(articleId, true);
        Refuse refuse = new Refuse();
        refuse.setArticleId(articleId);
        refuse.setExpireDate(DateUtil.offsetHour(new Date(), 24 * 15));
        // 强制保留15天
        refuseRepository.save(refuse);
    }


}
