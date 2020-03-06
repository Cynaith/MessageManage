package com.ly.messagemanage.Mapper;

import com.ly.messagemanage.Domain.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.Mapping;

/**
 * @USER: lynn
 * @DATE: 2020/3/7
 **/
@Repository
public interface UserMapping {

    @Select("select count(id) from user where name = #{name} and password = #{password}")
    public int login(User user);
}
