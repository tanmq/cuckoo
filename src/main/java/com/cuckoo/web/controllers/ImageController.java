package com.cuckoo.web.controllers;

import com.alibaba.fastjson.JSONObject;
import com.cuckoo.web.common.Constant;
import com.cuckoo.web.utils.ImageUtil;
import com.cuckoo.web.utils.OssUtil;
import com.cuckoo.web.utils.RespUtil;
import com.cuckoo.web.utils.StringUtil;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


/**
 * Created by tanmq on 2017/2/27.
 */
@Controller
@RequestMapping("/image")
public class ImageController {

    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject upload(@RequestParam("file")MultipartFile file, @Param("size")String size) throws Exception{
        byte[] imageData = file.getBytes();
        byte[] thumbData;
        if (StringUtil.NullOrEmpty(size)) {
            thumbData = ImageUtil.thumbnailImageDefault(imageData);
        } else {
            int width   = Constant.DEFAULT_IMAGE_WIDTH;
            int height  = Constant.DEFAULT_IMAGE_HEIGHT;
            String[] strs = size.split("x");
            if (strs.length == 2) {
                width   = Integer.parseInt(strs[0]);
                height  = Integer.parseInt(strs[1]);
            }

            thumbData = ImageUtil.thumbnailImage(imageData, width, height);
        }
        String url = OssUtil.uploadFile(imageData);
        String thumbUrl = OssUtil.uploadFile(thumbData);
        JSONObject data = new JSONObject();
        data.put("url", url);
        data.put("thumb_url", thumbUrl);
        return RespUtil.OKResponse(data);
    }



}
