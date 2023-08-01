package com.shuishu.blog.common.domain.article.entity.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author ：谁书-ss
 * @Date ：2023-05-02 17:44
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：标签访问排行vo
 * <p></p>
 */
@Schema(description = "标签访问排行vo")
@Setter
@Getter
@ToString
public class LabelVisitRankVo {

    @Schema(description = "标签名称")
    private String labelName;

    @Schema(description = "标签访问次数")
    private Integer labelVisitCount;

}
