package com.shuishu.blog.common.domain.label.entity.dto;


import com.shuishu.blog.common.config.base.BaseDTO;
import com.shuishu.blog.common.domain.label.entity.po.Label;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ：谁书-ss
 * @date ：2023-04-10 12:49
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：标签
 * <p></p>
 */
@Setter
@Getter
@ToString
public class LabelDTO extends BaseDTO<Label> {

    private Long labelId;

    private String labelName;

    private String labelDesc;

    private Integer labelSort;

}
