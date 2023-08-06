package com.shuishu.blog.business.label.service;


import com.shuishu.blog.common.config.base.PageDTO;
import com.shuishu.blog.common.config.base.PageVO;
import com.shuishu.blog.common.domain.label.entity.dto.LabelAddDto;
import com.shuishu.blog.common.domain.label.entity.dto.LabelQueryDto;
import com.shuishu.blog.common.domain.label.entity.dto.LabelUpdateDto;
import com.shuishu.blog.common.domain.label.entity.vo.LabelVo;

/**
 * @Author ：谁书-ss
 * @Date ：2023-04-03 22:37
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：标签
 * <p></p>
 */
public interface LabelService {
    /**
     * 添加标签
     *
     * @param labelAddDto 标签信息
     */
    void addLabel(LabelAddDto labelAddDto);

    /**
     * 分页查询
     *
     * @param labelQueryDto 查询条件
     * @param pageDTO       分页信息
     * @return 分页数据
     */
    PageVO<LabelVo> findLabelPage(LabelQueryDto labelQueryDto, PageDTO pageDTO);

    /**
     * 标签详情
     *
     * @param labelId 标签id
     * @return 标签信息
     */
    LabelVo findLabelDetails(Long labelId);

    /**
     * 更新标签
     *
     * @param labelUpdateDto 更新数据
     */
    void updateLabel(LabelUpdateDto labelUpdateDto);

    /**
     * 删除标签
     *
     * @param labelId -标签id
     */
    void deleteLabel(Long labelId);
}
