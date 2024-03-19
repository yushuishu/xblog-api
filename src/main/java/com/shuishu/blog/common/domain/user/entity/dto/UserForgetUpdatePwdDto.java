package com.shuishu.blog.common.domain.user.entity.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author ：谁书-ss
 * @Date ：2023-04-29 16:22
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：忘记密码dto
 * <p></p>
 */
@Schema(description = "忘记密码dto")
@Setter
@Getter
@ToString
public class UserForgetUpdatePwdDto {
    @NotBlank(message = "邮箱号不能为空")
    @Schema(description = "邮箱号")
    private String userEmail;

    @NotBlank(message = "新密码不能为空")
    @Schema(description = "新密码")
    private String password;

    @NotBlank(message = "确认密码不能为空")
    @Schema(description = "确认密码")
    private String ackPassword;

    @NotBlank(message = "邮箱验证码不能为空")
    @Schema(description = "邮箱验证码")
    private String captcha;

}
