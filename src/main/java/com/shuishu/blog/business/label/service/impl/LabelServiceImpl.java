package com.shuishu.blog.business.label.service.impl;


import com.shuishu.blog.business.label.service.LabelService;
import com.shuishu.blog.common.config.base.PageDTO;
import com.shuishu.blog.common.config.base.PageVO;
import com.shuishu.blog.common.domain.article.dsl.ArticleLabelDsl;
import com.shuishu.blog.common.domain.label.dsl.LabelDsl;
import com.shuishu.blog.common.domain.label.entity.dto.LabelAddDto;
import com.shuishu.blog.common.domain.label.entity.dto.LabelQueryDto;
import com.shuishu.blog.common.domain.label.entity.dto.LabelUpdateDto;
import com.shuishu.blog.common.domain.label.entity.po.Label;
import com.shuishu.blog.common.domain.label.entity.vo.LabelVo;
import com.shuishu.blog.common.domain.label.repository.LabelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;

/**
 * @Author ：谁书-ss
 * @Date ：2023-04-03 22:37
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：标签
 * <p></p>
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class LabelServiceImpl implements LabelService {
    private final LabelRepository labelRepository;
    private final LabelDsl labelDsl;
    private final ArticleLabelDsl articleLabelDsl;


    @Override
    public void addLabel(LabelAddDto labelAddDto) {
        Label label = labelAddDto.toPo(Label.class);
        long count = labelRepository.count();
        count++;
        label.setLabelSort((int) count);
    }

    @Override
    public PageVO<LabelVo> findLabelPage(LabelQueryDto labelQueryDto, PageDTO pageDTO) {
        return labelDsl.findLabelPage(labelQueryDto, pageDTO);
    }

    @Override
    public LabelVo findLabelDetails(Long labelId) {
        return labelDsl.findLabelDetails(labelId);
    }

    @Override
    public void updateLabel(LabelUpdateDto labelUpdateDto) {
        Label label = labelRepository.findByLabelId(labelUpdateDto.getLabelId());
        Objects.requireNonNull(label, "标签不存在");
        label.setLabelName(labelUpdateDto.getLabelName());
        label.setLabelDesc(labelUpdateDto.getLabelDesc());
        label.setLabelSort(labelUpdateDto.getLabelSort());
        label.setUpdateDate(new Date());
        labelRepository.save(label);
    }

    @Override
    public void deleteLabel(Long labelId) {
        // 删除文章关联的标签
        articleLabelDsl.deleteByLabelId(labelId);
        // 删除标签
        labelRepository.deleteById(labelId);
    }

}
