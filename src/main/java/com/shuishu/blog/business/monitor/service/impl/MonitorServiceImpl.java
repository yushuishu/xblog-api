package com.shuishu.blog.business.monitor.service.impl;


import com.shuishu.blog.business.monitor.service.MonitorService;
import com.shuishu.blog.common.config.base.PageDTO;
import com.shuishu.blog.common.config.base.PageVO;
import com.shuishu.blog.common.domain.article.entity.dto.ArticleVisitRankDto;
import com.shuishu.blog.common.domain.article.entity.vo.ArticleVisitRankVo;
import com.shuishu.blog.common.domain.article.entity.vo.LabelVisitRankVo;
import com.shuishu.blog.common.domain.monitor.entity.dto.ApiLogRecordDto;
import com.shuishu.blog.common.domain.monitor.entity.vo.ApiLogRecordVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class MonitorServiceImpl implements MonitorService {
    @Override
    public PageVO<ArticleVisitRankVo> findArticleVisitRankPage(ArticleVisitRankDto articleVisitRankDto, PageDTO pageDTO) {
        return null;
    }

    @Override
    public List<LabelVisitRankVo> findLabelVisitRankList() {
        return null;
    }

    @Override
    public PageVO<ApiLogRecordVo> findSystemApiLogPage(ApiLogRecordDto apiLogRecordDto, PageDTO pageDTO) {
        return null;
    }
}
