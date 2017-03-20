package com.cuckoo.web.utils;

import java.util.UUID;

/**
 * Created by tanmq on 2017/3/20.
 */
public class IDUtil {

    public static String newShareCode() {
        String random = UUID.randomUUID().toString();
        return EncodeUtil.MD5("1");
    }


    public static void main(String[] args) {
        System.out.println(newShareCode());
    }



}
