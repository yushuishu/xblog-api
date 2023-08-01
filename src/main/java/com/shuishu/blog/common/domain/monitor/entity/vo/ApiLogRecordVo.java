package com.shuishu.blog.common.domain.monitor.entity.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author ：谁书-ss
 * @Date ：2023-05-02 17:27
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：系统api日志vo
 * <p></p>
 */
@Schema(description = "系统api日志vo")
@Setter
@Getter
@ToString
public class ApiLogRecordVo {
    @Schema(description = "系统API日志id")
    private Long apiLogId;

    @Schema(description = "客户端ip地址：127.0.0.1")
    private String apiLogRemoteIp;

    @Schema(description = "客户端ip地址：河南省郑州市二七区")
    private String apiLogRemoteAddress;

    @Schema(description = "接口描述")
    private String apiLogDescription;

    @Schema(description = "接口url")
    private String apiLogUrl;

    @Schema(description = "请求方法：POST/GET")
    private String apiLogMethod;

    @Schema(description = "请求参数")
    private String apiLogRequestParam;

    @Schema(description = "响应数据")
    private String apiLogResponseData;

    @Schema(description = "请求时间")
    private String apiLogRequestDate;

    @Schema(description = "响应时间")
    private String apiLogResponseDate;

    @Schema(description = "响应状态：SUCCESS/FAIL")
    private String apiLogResponseStatus;

    @Schema(description = "用户昵称")
    private Long nickname;

}
