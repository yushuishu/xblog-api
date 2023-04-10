package com.shuishu.blog.common.domain.label.entity.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.shuishu.blog.common.config.base.BaseVO;
import com.shuishu.blog.common.domain.label.entity.po.Label;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author ：谁书-ss
 * @date ：2023-04-10 12:55
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：标签
 * <p></p>
 */
@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class LabelVO extends BaseVO<Label> {
    private Long labelId;

    private String labelName;

    private String labelDesc;

    private Integer labelSort;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

}
