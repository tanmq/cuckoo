package com.cuckoo.web.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by tanmq on 2017/2/28.
 */
@Controller
public class CuckooController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "cuckoo/index";
    }


}
