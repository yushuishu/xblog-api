package com.shuishu.blog.common.domain.user.entity.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author ：谁书-ss
 * @Date ：2023-03-12 16:48
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：缓存登录 Token
 * <p></p>
 */
@Setter
@Getter
@ToString
public class AuthTokenCacheDto {

    private String authToken;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
