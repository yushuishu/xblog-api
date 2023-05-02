package com.shuishu.blog.common.domain.user.entity.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author ：谁书-ss
 * @date ：2023-04-29 17:27
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：用户详情信息vo
 * <p></p>
 */
@Schema(description = "用户详情信息dto")
@Setter
@Getter
@ToString
public class UserDetailsVo {

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "个人简介")
    private String userAbout;

    @Schema(description = "头像")
    private String userPhoto;

    @Schema(description = "地址")
    private String userAddress;

    @Schema(description = "行业")
    private String industryName;

    @Schema(description = "用户过期true：没有过期  false：过期")
    private Boolean userIsAccountNonExpired;

    @Schema(description = "用户锁定true：没有锁定  false：被锁定")
    private Boolean userIsAccountNonLocked;

    @Schema(description = "最后一次登录时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date userAuthLastLoginDate;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    @Schema(description = "更新用户名称")
    private Date updateNickname;

    @Schema(description = "用户授权详情list")
    private List<UserAuthDetailsVo> userAuthDetailsVoList;

}
