package com.shuishu.blog.common.utils;


import cn.hutool.core.util.RandomUtil;
import com.shuishu.blog.common.domain.user.entity.po.UserAuth;
import com.shuishu.blog.common.domain.user.repository.UserAuthRepository;
import com.shuishu.blog.common.enums.UserEnum;
import com.shuishu.blog.common.utils.lock.ServiceLock;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @Author ：谁书-ss
 * @Date ：2024-03-15 8:44
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：用户注册，本系统账号生成
 * <p></p>
 */
@Component
public class UserIdUtils {
    @Resource
    private UserAuthRepository userAuthRepository;

    @ServiceLock
    public String generateSystemUsername() {
        // 尝试三次
        for (int i = 0; i < 3; i++) {
            int username = RandomUtil.randomInt(5, 10);
            UserAuth userAuth = userAuthRepository.findByUserAuthIdentifierAndAndUserAuthType(String.valueOf(username), UserEnum.AuthType.LOCAL.getType());
            if (userAuth == null) {
                return String.valueOf(username);
            }
        }
        return null;
    }


}
