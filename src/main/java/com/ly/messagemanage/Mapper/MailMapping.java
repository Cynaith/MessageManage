package com.ly.messagemanage.Mapper;

import com.ly.messagemanage.Entity.MailInfo;
import org.apache.ibatis.annotations.Insert;
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

    @Insert("insert into mailLog(userId,mailFrom,mailTo,mailSubject,mailText,mailTime) values(#{userId},#{mailFrom},#{mailTo},#{mailSubject},#{mailText},#{time})")
    public void addMailLog(int userId, String mailFrom, String mailTo, String mailSubject, String mailText,String time);
}
