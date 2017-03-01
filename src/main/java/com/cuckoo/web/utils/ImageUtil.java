package com.cuckoo.web.utils;

import com.cuckoo.web.common.Constant;
import net.coobird.thumbnailator.Thumbnailator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by tanmq on 2017/2/27.
 */
public class ImageUtil {

    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    /**
     * s生成缩略图
     * @param data
     * @return
     */
    public static byte[] thumbnailImage(byte[] data, int width, int height) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Thumbnailator.createThumbnail(new ByteArrayInputStream(data), outputStream, width, height);
            return outputStream.toByteArray();
        } catch (IOException e) {
            logger.error("Thumbnail image failed.", e);
        }

        return data;
    }


    public static byte[] thumbnailImageDefault(byte[] data) {
        return thumbnailImage(data, Constant.DEFAULT_IMAGE_WIDTH, Constant.DEFAULT_IMAGE_HEIGHT);
    }


}
