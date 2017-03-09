package com.cuckoo.web.utils;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;

import java.io.ByteArrayInputStream;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by tanmq on 2017/2/27.
 */
public class OssUtil {

    private static final String endPoint        = Config.get("oss.endpoint");
    private static final String endPointUrl     = "http://" + endPoint;
    private static final String accessKey       = Config.get("oss.accessKey");
    private static final String accessSecret    = Config.get("oss.accessSecret");
    private static final String buckName        = Config.get("oss.buckName");

    private static AtomicBoolean started = new AtomicBoolean(false);

    private static OSSClient ossClient;

    static {
        if (started.compareAndSet(false, true)) {
            ClientConfiguration conf = new ClientConfiguration();
            conf.setSupportCname(true);
            ossClient = new OSSClient(endPointUrl, accessKey, accessSecret, conf);
        }
    }


    public static String uploadFile(byte[] data) {
        String key = UUID.randomUUID().toString().replaceAll("-", "");
        PutObjectResult result = ossClient.putObject(buckName, key, new ByteArrayInputStream(data));

        String url = "http://" + buckName + "." + endPoint + "/" + key;

        return url;
    }


}
