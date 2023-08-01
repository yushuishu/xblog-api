package com.shuishu.blog.common.utils;


import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * @Author ：谁书-ss
 * @Date ：2023-04-20 23:25
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：编码相关工具
 * <p></p>
 */
public class CodingUtils {

    public static String convertByteToBase64(byte[] byteArray) {
        if (byteArray != null && byteArray.length > 0) {
            return Base64.getEncoder().encodeToString(byteArray);
        }
        return null;
    }

    public static byte[] convertBase64ToByte(String base64Str) {
        if (StringUtils.hasText(base64Str)) {
            return Base64.getDecoder().decode(base64Str);
        }
        return null;
    }

    public static String convertFileToBase64(File imgFile) {
        if (imgFile != null && imgFile.exists()) {
            try (FileInputStream fis = new FileInputStream(imgFile)) {
                byte[] bytes = new byte[(int) imgFile.length()];
                fis.read(bytes);
                return convertByteToBase64(bytes);
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }
        return null;
    }

}
