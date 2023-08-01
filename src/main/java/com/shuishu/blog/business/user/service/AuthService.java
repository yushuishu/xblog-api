package com.shuishu.blog.business.user.service;


import com.shuishu.blog.common.domain.user.entity.vo.UserInfoVo;
import com.shuishu.blog.common.enums.UserEnum;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author ：谁书-ss
 * @Date ：2023/3/7 14:41
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：
 */
public interface AuthService {
    /**
     * 登录授权
     *
     * @param name -
     * @param pwd -
     * @param authType -
     * @param isRememberMe -
     * @param response -
     * @return -
     */
    UserInfoVo login(String name, String pwd, UserEnum.AuthType authType, Boolean isRememberMe, HttpServletResponse response);
}
