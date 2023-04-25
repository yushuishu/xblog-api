package com.shuishu.blog.common.utils;


import org.springframework.util.StringUtils;

import java.util.Base64;

/**
 * @author ：谁书-ss
 * @date ：2023-04-20 23:25
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：编码相关工具
 * <p></p>
 */
public class CodingUtils {

    public static String byteToBase64(byte[] byteArray) {
        if (byteArray != null && byteArray.length > 0) {
            return Base64.getEncoder().encodeToString(byteArray);
        }
        return null;
    }

    public static byte[] base64ToByte(String base64Str) {
        if (StringUtils.hasText(base64Str)) {
            return Base64.getDecoder().decode(base64Str);
        }
        return null;
    }


}
