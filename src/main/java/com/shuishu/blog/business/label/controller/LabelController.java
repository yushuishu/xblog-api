package com.shuishu.blog.business.label.controller;


import com.shuishu.blog.business.label.service.LabelService;
import com.shuishu.blog.common.config.base.ApiResponse;
import com.shuishu.blog.common.config.base.PageDTO;
import com.shuishu.blog.common.config.base.PageVO;
import com.shuishu.blog.common.domain.label.entity.dto.LabelAddDto;
import com.shuishu.blog.common.domain.label.entity.dto.LabelQueryDto;
import com.shuishu.blog.common.domain.label.entity.dto.LabelUpdateDto;
import com.shuishu.blog.common.domain.label.entity.vo.LabelVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author ：谁书-ss
 * @Date ：2023-04-03 22:36
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：标签
 * <p></p>
 */
@Tag(name = "文章标签")
@RequiredArgsConstructor
@RestController
@RequestMapping("label")
public class LabelController {
    private final LabelService labelService;


    @Operation(summary = "添加", description = "添加标签")
    @PostMapping("add")
    public ApiResponse<String> addLabel(@RequestBody @Validated LabelAddDto labelAddDto) {
        labelService.addLabel(labelAddDto);
        return ApiResponse.success();
    }

    @Operation(summary = "分页", description = "分页查询标签")
    @GetMapping("page")
    public ApiResponse<PageVO<LabelVo>> findLabelPage(LabelQueryDto labelQueryDto, PageDTO pageDTO) {
        return ApiResponse.of(labelService.findLabelPage(labelQueryDto, pageDTO));
    }

    @Operation(summary = "详情", description = "查询标签详情")
    @GetMapping("details")
    public ApiResponse<LabelVo> findLabelDetails(Long labelId) {
        return ApiResponse.of(labelService.findLabelDetails(labelId));
    }

    @Operation(summary = "更新", description = "更新标签")
    @PostMapping("update")
    public ApiResponse<String> updateLabel(@RequestBody @Validated LabelUpdateDto labelUpdateDto) {
        labelService.updateLabel(labelUpdateDto);
        return ApiResponse.success();
    }

    @Operation(summary = "删除", description = "删除标签")
    @PostMapping("delete")
    public ApiResponse<String> deleteLabel(Long labelId) {
        labelService.deleteLabel(labelId);
        return ApiResponse.success();
    }

}
