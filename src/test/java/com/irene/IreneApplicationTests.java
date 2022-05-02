package com.irene;
import com.irene.Entity.User;
import com.irene.Mapper.ActivityIdMapper;
import com.irene.Mapper.ActivityMapper;
import com.irene.Mapper.UserMapper;
import com.irene.Service.UserService;
import com.irene.common.number;
import com.irene.util.JwtHelper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootTest
class IreneApplicationTests {
@Autowired
private UserMapper userMapper;
    @Autowired
    JavaMailSender javaMailSender;
    @Test
    void contextLoads() {
        System.out.println(userMapper.Findall());
    }
        @Test
        public void sendSimpleMail() throws IOException, TemplateException, MessagingException {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
            message.setSubject("所依志愿服务");
            message.setFrom("soe-irene@qq.com");
            message.setTo("79006134@qq.com");
//        message.setCc("37xxxxx37@qq.com");
//        message.setBcc("14xxxxx098@qq.com");
            message.setSentDate(new Date());
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
            // 配置模板位置，启动类的位置JhjmailApplication
            ClassLoader loader = ClassLoader.getSystemClassLoader();
            configuration.setClassLoaderForTemplateLoading(loader, "templates");
            //加载模板
            Template template = configuration.getTemplate("mail.ftl");
            StringWriter out = new StringWriter();
            number num = new number();
            num.setNum("123323");
            template.process(num, out);
            message.setText(out.toString(),true);
            javaMailSender.send(mimeMessage);

    }

        @Test
    void  JWTdemo(){
            Long time = 1000*60*60*24*30l;
           String signature = "hgy23232";

            JwtBuilder builder = Jwts.builder();

            String token = builder.setHeaderParam("typ","JWT")
                    .setHeaderParam("alg","HS256")
                    .claim("user","张三")
                    .setSubject("test")

                    .setExpiration(new Date(System.currentTimeMillis() + time))
                    .setId(UUID.randomUUID().toString())
                    .signWith(SignatureAlgorithm.HS256, signature).compact();
            System.out.println(token);


        }

        @Test
    void opem(){
        String key="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoi5byg5LiJIiwic3ViIjoidGVzdCIsImV4cCI6MTY1NDA3NTc2NSwianRpIjoiN2E2NzMxMGEtMzA5OC00YzU5LTlhZWEtODgyZDM2ZjQxNjZlIn0.pq4oanZwQQ960FbYe5Dcqw77q8rS5EHfIyyPM9uI3kk";
            JwtParser parser = Jwts.parser();
            Jws<Claims> hg = parser.setSigningKey("hgy23232").parseClaimsJws(key);
            Claims body = hg.getBody();
            System.out.println(body.get("user")
);
        }
        @Test
    public void  demo1(){
            JwtHelper jwt = new JwtHelper();
            Long tokenId = jwt.getTokenId(jwt.getToken(1232132132132313123l));
            System.out.println(tokenId);

        }
}
