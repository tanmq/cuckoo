package com.cuckoo.web.utils;

import net.coobird.thumbnailator.Thumbnailator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by tanmq on 2017/2/27.
 */
public class ImageUtil {


    /**
     * s生成缩略图
     * @param data
     * @return
     */
    public static byte[] thumbnailImage(byte[] data) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Thumbnailator.createThumbnail(new ByteArrayInputStream(data), outputStream, 600, 600);
            return outputStream.toByteArray();
        } catch (IOException e) {

        }

        return data;
    }


}
