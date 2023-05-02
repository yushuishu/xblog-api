package com.shuishu.blog.common.domain.monitor.entity.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author ：谁书-ss
 * @date ：2023-05-02 17:32
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：系统api日志dto
 * <p></p>
 */
@Schema(description = "系统api日志vo")
@Setter
@Getter
@ToString
public class ApiLogRecordDto {
    @Schema(description = "搜索关键字：描述/url/ip/地址/")
    private String keyword;

    @Schema(description = "响应状态：SUCCESS/FAIL")
    private String apiLogResponseStatus;

    @Schema(description = "请求方法：POST/GET")
    private String apiLogMethod;

    @Schema(description = "开始日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date apiLogRequestStartDate;

    @Schema(description = "结束日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date apiLogRequestEndDate;

}
