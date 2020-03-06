package com.ly.messagemanage.Handler.Mail;

import com.ly.messagemanage.Entity.MailInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @USER: lynn
 * @DATE: 2020/3/5
 * 邮件生产者
 **/
//调用时可Autowired
@Component
public class MailProducer implements RabbitTemplate.ConfirmCallback {

    @Autowired
    //注入消息模版
    private RabbitTemplate rabbitTemplate;

    @Value("${messagemanage.fanout.exchange}")
    private String exchangeName;

    //路由Key
    @Value("{messagemanage.fanout.mail.queue}")
    private String routeKey;

    static boolean isSend;

    static boolean isConfirm;

    private MailInfo mailInfo;

    static int confirmCount = 0;

    public boolean sendMessage(MailInfo mailInfo) {
        isSend = false;
        //判断confirm是否生效
        isConfirm = false;

        this.mailInfo = mailInfo;

        RabbitTemplate.ReturnCallback returnCallback = (message, replyCode, replyText, exchange, routingKey) -> {
            System.out.println("MsgSendReturnCallback message：" + message);
            System.out.println("MsgSendReturnCallback replyCode：" + replyCode);
            System.out.println("MsgSendReturnCallback replyText：" + replyText);
            System.out.println("MsgSendReturnCallback exchange：" + exchange);
            System.out.println("MsgSendReturnCallback routingKey：" + routingKey);
        };
        rabbitTemplate.setReturnCallback(returnCallback);
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.convertAndSend(exchangeName, routeKey, mailInfo);
        //等待confirm结束 返回confirm结果
        //如不等待，会直接false返回
        while (!isConfirm) ;
        return isSend;

    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String s) {
        if (!ack&&confirmCount++<5) {
            System.out.println("RabbitMQ Writing error!");
            System.out.println("Attempting to write again...");
            System.out.println("Mail Infomation:"+mailInfo.toString());
            this.sendMessage(mailInfo);
        } else {
            System.out.println("MailProducer->" + exchangeName + ":" + true);
            isSend = true;
        }
        isConfirm = true;
    }
}
