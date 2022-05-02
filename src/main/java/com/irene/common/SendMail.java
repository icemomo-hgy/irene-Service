package com.irene.common;

import com.irene.IreneApplication;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Component;

/**
 * @author 黄冠瑛
 * @date 2022/5/3
 * 邮箱发送工具类
 *
 * 需要参数  1自定义验证码code   2邮箱字符串     3 邮箱模板
 */

@Component
public class SendMail {
    @Autowired
    private  JavaMailSender  javaMailSender;
    public void sendSimpleMail(String code,String email,String model) throws IOException, TemplateException, MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
        message.setSubject("所依志愿服务");
        message.setFrom("soe-irene@qq.com");
        System.out.println(email);
        message.setTo(email.toString());
//        message.setCc("37xxxxx37@qq.com");
//        message.setBcc("14xxxxx098@qq.com");
        message.setSentDate(new Date());
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
        // 配置模板位置，启动类的位置JhjmailApplication
        ClassLoader loader = IreneApplication.class.getClassLoader();
        configuration.setClassLoaderForTemplateLoading(loader, "templates");
        //加载模板

        Template template = configuration.getTemplate(model);
        StringWriter out = new StringWriter();
        number num = new number();
        num.setNum(code);
        template.process(num, out);
        message.setText(out.toString(),true);
        javaMailSender.send(mimeMessage);

    }
}
