package com.shuishu.blog.common.domain.user.entity.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author ：谁书-ss
 * @Date ：2023-05-02 14:54
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：用户授权详情vo
 * <p></p>
 */
@Schema(description = "用户授权详情vo")
@Setter
@Getter
@ToString
public class UserAuthDetailsVo {
    @Schema(description = "用户授权id")
    private Long userAuthId;

    @Schema(description = "授权类型")
    private String userAuthType;

    @Schema(description = "登录号：唯一识别码")
    private String userAuthIdentifier;

    @Schema(description = "授权昵称")
    private String userAuthNickname;
}
