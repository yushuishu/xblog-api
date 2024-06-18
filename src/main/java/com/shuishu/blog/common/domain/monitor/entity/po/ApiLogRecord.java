package com.shuishu.blog.common.domain.monitor.entity.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shuishu.blog.common.config.base.BasePO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @Author ：谁书-ss
 * @Date ：2023-05-02 17:10
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：系统API日志
 * <p></p>
 */
@Setter
@Getter
@ToString
@TableName("ss_api_log_record")
@Comment(value = "系统API日志表")
public class ApiLogRecord {
    @TableId(value = "api_log_id", type = IdType.ASSIGN_ID)
    @Comment(value = "系统API日志id")
    private Long apiLogId;

    @TableField("api_log_remote_ip")
    @Comment("客户端ip地址：127.0.0.1")
    @Column(nullable = false, unique = true)
    private String apiLogRemoteIp;

    @TableField("api_log_remote_address")
    @Comment("客户端ip地址：河南省郑州市二七区")
    @Column(nullable = false, unique = true)
    private String apiLogRemoteAddress;

    @TableField("api_log_description")
    @Comment("接口描述")
    @Column(nullable = false, unique = true)
    private String apiLogDescription;

    @TableField("api_log_url")
    @Comment("接口url")
    @Column(nullable = false, unique = true)
    private String apiLogUrl;

    @TableField("api_log_method")
    @Comment("请求方法：POST/GET")
    @Column(nullable = false, unique = true)
    private String apiLogMethod;

    @TableField("api_log_request_data")
    @Comment("请求数据")
    private String apiLogRequestData;

    @TableField("api_log_response_data")
    @Comment("响应数据")
    private String apiLogResponseData;

    @TableField("api_log_request_date")
    @Comment("请求时间")
    private String apiLogRequestDate;

    @TableField("api_log_response_date")
    @Comment("响应时间")
    private String apiLogResponseDate;

    @TableField("api_log_response_status")
    @Comment("响应状态：SUCCESS/FAIL")
    private String apiLogResponseStatus;

    @TableField("api_log_user_id")
    @Comment("请求用户id")
    private Long apiLogUserId;



}
