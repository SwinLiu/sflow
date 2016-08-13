package com.lyplay.sflow.common.mail;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SpringMail implements MailSender {
	
	private static Logger log = LoggerFactory.getLogger(SpringMail.class);
    
	//注入一个mailSender
    @Autowired
    JavaMailSenderImpl mailSender;
    
	@Override
	public void sendMail() {
		//读取配置文件中的收件人
        String[] to = {"982563575@qq.com"};// 收件人
        String[] cc = {};// 收件人

        MimeMessage mailMessage = mailSender.createMimeMessage();
        
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true, "utf-8");
            helper.setFrom("liu_ymail@126.com");// 设置发件人
            helper.setTo(to);// 设置收件人
            helper.setCc(cc);// 设置抄送
            helper.setSubject("SpringMail测试");// 设置主题
            helper.setText("这是一封来自SpringMail的测试邮件");// 邮件体
            mailSender.send(mailMessage);// 发送邮件
            log.info("邮件发送成功...");
        } catch (Exception e) {
            log.error("邮件发送发生异常");
            log.error(e.getMessage());
            log.info("进行重发");
            try {
                Thread.sleep(1000 * 1000);
                this.sendMail();
            } catch (InterruptedException e1) {
                log.info("重发邮件发生异常");
                log.error(e.getMessage());
                e1.printStackTrace();
            }
        }
	}

}
