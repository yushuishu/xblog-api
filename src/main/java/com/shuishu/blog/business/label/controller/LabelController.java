package com.shuishu.blog.business.label.controller;


import com.shuishu.blog.business.label.service.LabelService;
import com.shuishu.blog.common.config.base.ApiResponse;
import com.shuishu.blog.common.config.base.PageDTO;
import com.shuishu.blog.common.domain.label.entity.dto.LabelDTO;
import com.shuishu.blog.common.domain.label.entity.vo.LabelVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：谁书-ss
 * @date ：2023-04-03 22:36
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：标签
 * <p></p>
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("label")
public class LabelController {
    private final LabelService labelService;


    @PostMapping("add")
    public ApiResponse<String> addLabel(@RequestBody LabelDTO labelDTO) {
        labelService.addLabel(labelDTO);
        return ApiResponse.success();
    }

    @GetMapping("page")
    public ApiResponse<Page<LabelVO>> findLabelPage(LabelDTO labelDTO, PageDTO pageDTO) {
        return ApiResponse.of(labelService.findLabelPage(labelDTO, pageDTO));
    }

    @GetMapping("details")
    public ApiResponse<LabelVO> findLabelDetails(Long labelId) {
        return ApiResponse.of(labelService.findLabelDetails(labelId));
    }

    @PostMapping("update")
    public ApiResponse<String> updateLabel(@RequestBody LabelDTO labelDTO) {
        labelService.updateLabel(labelDTO);
        return ApiResponse.success();
    }

}
