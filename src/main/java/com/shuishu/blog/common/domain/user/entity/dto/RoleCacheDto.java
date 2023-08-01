package com.shuishu.blog.common.domain.user.entity.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author ：谁书-ss
 * @Date ：2023-03-10 23:23
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：缓存 角色集合
 * <p></p>
 */
@Setter
@Getter
@ToString
public class RoleCacheDto {
    private String permissionCode;

    private String permissionUrl;

    private Boolean isNeedAuthorization;

}
