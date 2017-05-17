package com.cuckoo.web.controllers;

import com.cuckoo.web.mysql.dao.FeedDao;
import com.cuckoo.web.mysql.dao.FeedTimelineDao;
import com.cuckoo.web.mysql.dao.UserDao;
import com.cuckoo.web.mysql.ddl.Feed;
import com.cuckoo.web.mysql.ddl.User;
import com.cuckoo.web.utils.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by tanmq on 2017/2/26.
 */
@Controller
public class HelloController {

    public static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    FeedDao feedDao;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        logger.info("I am become an log here!!!!!!!!!!!");
        logger.info("{}", userDao.getAll().size());
        List<User> users = userDao.getAll();
        users.forEach( u -> logger.info("name : {}", u.getName()));
        userDao.updateUserArea(6, "北京东城区");
        MessageUtil.sendCode("15902098344", 7890);
        return "hello";
    }


    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public String demo() {
        return "cuckoo/topicDemo";
    }



    public static void main(String[] args) {
        System.out.println();
        Optional<String> optional = Optional.ofNullable("hi");
        if (optional.isPresent()) {
            System.out.println(optional.get());
        } else {
            System.out.println("null");
        }
    }


}
