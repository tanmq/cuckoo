package com.cuckoo.web.mysql.mapper;

import com.cuckoo.web.mysql.ddl.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by tanmq on 2017/2/26.
 */
@Mapper
public interface UserMapper {

    @Select("select * from user")
    public List<User> getALl();

}
