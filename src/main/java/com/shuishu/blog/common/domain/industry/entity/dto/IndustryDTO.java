package com.shuishu.blog.common.domain.industry.entity.dto;


import com.shuishu.blog.common.config.base.BaseDTO;
import com.shuishu.blog.common.domain.industry.entity.po.Industry;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ：谁书-ss
 * @date ：2023-03-30 12:46
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：行业dto
 * <p></p>
 */
@Setter
@Getter
@ToString
public class IndustryDTO extends BaseDTO<Industry> {
    private Long industryId;

    private Long industryName;

    private Integer industrySort;
}
