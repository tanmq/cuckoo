package com.cuckoo.web.controllers;

import com.cuckoo.web.mysql.dao.UserDao;
import com.cuckoo.web.mysql.ddl.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by tanmq on 2017/2/26.
 */
@Controller
public class HelloController {

    public static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        logger.info("I am become an log here!!!!!!!!!!!");
        logger.info("{}", userDao.getAll().size());
        List<User> users = userDao.getAll();
        users.forEach( u -> logger.info("name : {}", u.getName()));
        return "hello";
    }


    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public String demo() {
        return "cuckoo/topicDemo";
    }


}
