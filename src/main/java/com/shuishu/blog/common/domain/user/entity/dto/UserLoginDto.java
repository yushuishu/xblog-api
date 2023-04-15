package com.shuishu.blog.common.domain.user.entity.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author wuZhenFeng
 * @date 2023/3/7 15:44
 */
@Schema(description = "登录dto")
@Setter
@Getter
@ToString
public class UserLoginDto {
    @Schema(description = "账号")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "记住我")
    private Boolean isRememberMe;

}
