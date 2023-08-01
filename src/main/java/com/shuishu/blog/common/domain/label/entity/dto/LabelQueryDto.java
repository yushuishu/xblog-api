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
public class LabelQueryDto extends BaseDTO<Label> {

    @Schema(description = "标签名称和描述")
    private String keyword;

    @Schema(description = "标签排序:小于多少")
    private Integer labelSort;

}
