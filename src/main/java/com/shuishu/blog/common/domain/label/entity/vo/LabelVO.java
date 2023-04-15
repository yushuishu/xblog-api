package com.shuishu.blog.common.domain.label.entity.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.shuishu.blog.common.config.base.BaseVO;
import com.shuishu.blog.common.domain.label.entity.po.Label;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author ：谁书-ss
 * @date ：2023-04-10 12:55
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：标签vo
 * <p></p>
 */
@Schema(description = "标签vo")
@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class LabelVO extends BaseVO<Label> {
    @Schema(description = "标签id")
    private Long labelId;

    @Schema(description = "标签名称")
    private String labelName;

    @Schema(description = "标签描述，以|分隔")
    private String labelDesc;

    @Schema(description = "标签排序")
    private Integer labelSort;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

}
