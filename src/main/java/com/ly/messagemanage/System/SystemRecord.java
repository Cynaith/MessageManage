package com.ly.messagemanage.System;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @USER: lynn
 * @DATE: 2020/3/5
 **/
@NoArgsConstructor
@Data
public class SystemRecord {
    private static SystemRecord systemRecord;

    //用户设置 发送邮箱

    private String userMail;

    //发送邮箱数量
    private int sendCount;

    public static synchronized SystemRecord getInstance(){
        if (null == systemRecord){
            systemRecord = new SystemRecord();
        }
        return systemRecord;
    }


}
