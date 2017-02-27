package com.cuckoo.web.controllers;

import com.alibaba.fastjson.JSONObject;
import com.cuckoo.web.utils.OssUtil;
import com.cuckoo.web.utils.RespUtil;
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
@RequestMapping("/file")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject upload(@RequestParam("file")MultipartFile file) throws Exception{
        String url = OssUtil.uploadFile(file.getBytes());
        JSONObject data = new JSONObject();
        data.put("url", url);
        return RespUtil.OKResponse(data);
    }



}
