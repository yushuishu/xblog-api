package com.shuishu.blog.business.monitor.service;


import com.shuishu.blog.common.config.base.PageDTO;
import com.shuishu.blog.common.config.base.PageVO;
import com.shuishu.blog.common.domain.article.entity.dto.ArticleVisitRankDto;
import com.shuishu.blog.common.domain.article.entity.vo.ArticleVisitRankVo;
import com.shuishu.blog.common.domain.article.entity.vo.LabelVisitRankVo;
import com.shuishu.blog.common.domain.monitor.entity.dto.ApiLogRecordDto;
import com.shuishu.blog.common.domain.monitor.entity.vo.ApiLogRecordVo;

import java.util.List;

/**
 * @Author ：谁书-ss
 * @Date ：2023-04-06 20:27
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：监控
 * <p></p>
 */
public interface MonitorService {
    /**
     * 文章访问排行
     *
     * @param articleVisitRankDto 查询条件
     * @param pageDTO 分页参数
     * @return 文章访问排行
     */
    PageVO<ArticleVisitRankVo> findArticleVisitRankPage(ArticleVisitRankDto articleVisitRankDto, PageDTO pageDTO);

    /**
     * 标签访问排行
     *
     * @return 标签访问排行
     */
    List<LabelVisitRankVo> findLabelVisitRankList();

    /**
     * 标签访问排行
     *
     * @param apiLogRecordDto 查询条件
     * @param pageDTO 分页参数
     * @return 标签访问排行
     */
    PageVO<ApiLogRecordVo> findSystemApiLogPage(ApiLogRecordDto apiLogRecordDto, PageDTO pageDTO);
}
