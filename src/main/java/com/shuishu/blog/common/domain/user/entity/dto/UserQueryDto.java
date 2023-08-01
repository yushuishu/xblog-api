package com.shuishu.blog.common.domain.user.entity.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author ：谁书-ss
 * @Date ：2023-04-29 17:31
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：分页查询用户信息dto
 * <p></p>
 */
@Schema(description = "分页查询用户信息dto")
@Setter
@Getter
@ToString
public class UserQueryDto {
    @Schema(description = "昵称/账号/简介")
    private String keyword;

    @Schema(description = "用户过期true：没有过期  false：过期")
    private Boolean userIsAccountNonExpired;

    @Schema(description = "用户锁定true：没有锁定  false：被锁定")
    private Boolean userIsAccountNonLocked;

    @Schema(description = "同时登录客户端的人数:最小1最大50（默认1）")
    private Integer userMaxLoginClientNumber;

    @Schema(description = "最后一次登录时间：开始范围")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date userLastLoginDateStartRange;

    @Schema(description = "最后一次登录时间：开始范围")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date userLastLoginDateEndRange;

}
