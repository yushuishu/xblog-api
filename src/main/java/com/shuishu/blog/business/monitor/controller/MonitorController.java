package com.shuishu.blog.business.monitor.controller;


import com.shuishu.blog.business.monitor.service.MonitorService;
import com.shuishu.blog.common.config.base.ApiResponse;
import com.shuishu.blog.common.config.base.PageDTO;
import com.shuishu.blog.common.config.base.PageVO;
import com.shuishu.blog.common.domain.article.entity.dto.ArticleVisitRankDto;
import com.shuishu.blog.common.domain.article.entity.vo.ArticleVisitRankVo;
import com.shuishu.blog.common.domain.article.entity.vo.LabelVisitRankVo;
import com.shuishu.blog.common.domain.monitor.entity.dto.ApiLogRecordDto;
import com.shuishu.blog.common.domain.monitor.entity.vo.ApiLogRecordVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@Tag(name = "监控")
@RequiredArgsConstructor
@RestController
@RequestMapping("monitor")
public class MonitorController {
    private final MonitorService monitorService;


    @Operation(summary = "文章访问排行", description = "文章访问排行")
    @GetMapping("article/visit/rank/page")
    public ApiResponse<PageVO<ArticleVisitRankVo>> findArticleVisitRankPage(ArticleVisitRankDto articleVisitRankDto, PageDTO pageDTO) {
        return ApiResponse.of(monitorService.findArticleVisitRankPage(articleVisitRankDto, pageDTO));
    }

    @Operation(summary = "标签访问排行", description = "标签访问排行")
    @GetMapping("label/visit/rank/list")
    public ApiResponse<List<LabelVisitRankVo>> findLabelVisitRankList() {
        return ApiResponse.of(monitorService.findLabelVisitRankList());
    }

    @Operation(summary = "标签访问排行", description = "标签访问排行")
    @GetMapping("system/api/log/page")
    public ApiResponse<PageVO<ApiLogRecordVo>> findSystemApiLogPage(ApiLogRecordDto apiLogRecordDto, PageDTO pageDTO) {
        return ApiResponse.of(monitorService.findSystemApiLogPage(apiLogRecordDto, pageDTO));
    }


}
