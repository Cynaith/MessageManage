package com.ly.messagemanage.Mapper;

import com.ly.messagemanage.Domain.Mail;
import com.ly.messagemanage.Domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    @Update("update user set clientkey = #{clientkey} where name = #{name}")
    public void setClientkey(User user);

    @Select("select count(id) from user where name = #{name} and clientkey = #{clientkey}")
    public int checkClient(User user);

    @Insert("insert into mail(userId,mail,password) " +
            "select" +
            "(select id from user where name = #{name}),#{mail},#{password}" +
            "from dual" +
            " where not exists(select mail from mail where userId = (select id from user where name = #{name}))" +
            "limit 0,1 ")
    public int initMail(@Param("mail") String mail,@Param("password") String password, @Param("name") String name);
}
