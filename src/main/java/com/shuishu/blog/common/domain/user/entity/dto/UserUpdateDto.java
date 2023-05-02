package com.shuishu.blog.common.domain.user.entity.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ：谁书-ss
 * @date ：2023-04-29 16:13
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：更新用户dto
 * <p></p>
 */
@Schema(description = "更新用户dto")
@Setter
@Getter
@ToString
public class UserUpdateDto {
    @NotNull(message = "用户id不能为空")
    @Schema(description = "用户id")
    private Long userId;

    @NotBlank(message = "昵称不能为空")
    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "个人简介")
    private String userAbout;

    @Schema(description = "头像")
    private String userPhoto;

    @Schema(description = "地址")
    private String userAddress;

    @Schema(description = "行业id")
    private Long industryId;

}
