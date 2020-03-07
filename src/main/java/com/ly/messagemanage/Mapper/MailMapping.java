package com.ly.messagemanage.Mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @USER: lynn
 * @DATE: 2020/3/7
 **/

@Repository
public interface MailMapping {

    @Select("select password from mail where mail = #{mail}")
    public String getPasswordByMail(@Param("mail") String mail);
}
