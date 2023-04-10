package com.shuishu.blog.business.label.service;


import com.shuishu.blog.common.config.base.PageDTO;
import com.shuishu.blog.common.domain.label.entity.dto.LabelDTO;
import com.shuishu.blog.common.domain.label.entity.vo.LabelVO;
import org.springframework.data.domain.Page;

/**
 * @author ：谁书-ss
 * @date ：2023-04-03 22:37
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：标签
 * <p></p>
 */
public interface LabelService {
    /**
     * 添加标签
     *
     * @param labelDTO 标签信息
     */
    void addLabel(LabelDTO labelDTO);

    /**
     * 分页查询
     *
     * @param labelDTO 查询条件
     * @param pageDTO 分页信息
     * @return 分页数据
     */
    Page<LabelVO> findLabelPage(LabelDTO labelDTO, PageDTO pageDTO);

    /**
     * 标签详情
     *
     * @param labelId 标签id
     * @return 标签信息
     */
    LabelVO findLabelDetails(Long labelId);

    /**
     * 更新标签
     *
     * @param labelDTO 更新数据
     */
    void updateLabel(LabelDTO labelDTO);
}
