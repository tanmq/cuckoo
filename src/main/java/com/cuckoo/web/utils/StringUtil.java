package com.cuckoo.web.utils;

/**
 * Created by tanmq on 2017/2/27.
 */
public class StringUtil {


    public static boolean NullOrEmpty(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        }

        return false;
    }

}
