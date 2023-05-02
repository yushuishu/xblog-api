package com.shuishu.blog.common.domain.monitor.entity.po;


import com.shuishu.blog.common.config.base.BasePO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author ：谁书-ss
 * @date ：2023-05-02 17:10
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：系统API日志
 * <p></p>
 */
@Setter
@Getter
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ss_api_log_record")
@Comment(value = "系统API日志表")
public class ApiLogRecord {
    @Id
    @GeneratedValue(generator = "CustomIdGenerator")
    @GenericGenerator(name = "CustomIdGenerator", strategy = "com.shuishu.blog.common.config.id.CustomIdGenerator")
    @Comment(value = "系统API日志id")
    private Long apiLogId;

    @Comment("客户端ip地址：127.0.0.1")
    @Column(nullable = false, unique = true)
    private String apiLogRemoteIp;

    @Comment("客户端ip地址：河南省郑州市二七区")
    @Column(nullable = false, unique = true)
    private String apiLogRemoteAddress;

    @Comment("接口描述")
    @Column(nullable = false, unique = true)
    private String apiLogDescription;

    @Comment("接口url")
    @Column(nullable = false, unique = true)
    private String apiLogUrl;

    @Comment("请求方法：POST/GET")
    @Column(nullable = false, unique = true)
    private String apiLogMethod;

    @Comment("请求参数")
    private String apiLogRequestParam;

    @Comment("响应数据")
    private String apiLogResponseData;

    @Comment("请求时间")
    private String apiLogRequestDate;

    @Comment("响应时间")
    private String apiLogResponseDate;

    @Comment("响应状态：SUCCESS/FAIL")
    private String apiLogResponseStatus;

    @Comment("请求用户id")
    private Long userId;



}
