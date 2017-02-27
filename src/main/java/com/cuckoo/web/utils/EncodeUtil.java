package com.cuckoo.web.utils;

import org.springframework.util.DigestUtils;
import org.springframework.util.SystemPropertyUtils;

import java.util.UUID;

/**
 * Created by tanmq on 2017/2/27.
 */
public class EncodeUtil {

    /**
     * 求content的md5值，获取16进制字符串表示
     * @param content
     * @return
     */
    public static String MD5(String content) {
        return DigestUtils.md5DigestAsHex(content.getBytes());
    }


    /**
     * 生成一个新的盐值
     * @param account
     * @return
     */
    public static String newSalt(String account) {
        String uuid = UUID.randomUUID().toString();

        return MD5(account + uuid);
    }

    public static String newSessionId() {
        String timestamp = String.valueOf(System.currentTimeMillis());

        return MD5(timestamp);
    }

}
