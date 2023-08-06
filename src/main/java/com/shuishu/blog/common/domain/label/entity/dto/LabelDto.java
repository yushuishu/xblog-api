package com.shuishu.blog.common.domain.label.entity.dto;


import com.shuishu.blog.common.config.base.BaseDTO;
import com.shuishu.blog.common.domain.label.entity.po.Label;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author ：谁书-ss
 * @Date ：2023-04-10 12:49
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：标签dto
 * <p></p>
 */
@Schema(description = "标签dto")
@Setter
@Getter
@ToString
public class LabelDto extends BaseDTO<Label> {

    @Schema(description = "标签id")
    private Long labelId;

    @Schema(description = "标签名称")
    private String labelName;

    @Schema(description = "标签描述，以|分隔")
    private String labelDesc;

    @Schema(description = "标签排序")
    private Integer labelSort;

}
