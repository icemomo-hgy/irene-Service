package com.irene.Controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.irene.Entity.User;
import com.irene.Entity.UserInfo;
import com.irene.Service.UserInfoService;
import com.irene.Service.UserService;
import com.irene.common.Code;
import com.irene.common.Result;
import com.irene.common.SendMail;
import com.irene.common.ValidateCodeUtils;
import com.irene.util.JwtHelper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.springframework.util.DigestUtils;
@Slf4j
@RestController
public class UserConrtoller {
    @Autowired
    private UserService userService;
    @Autowired
    private SendMail sendMail;
    @Autowired
    private UserInfoService userInfoService;
    @SneakyThrows
    //发送邮箱验证码
    @PostMapping("/sendEmail")
    public Result<String> sendEmail( String email, HttpSession session){
        Integer integer = ValidateCodeUtils.generateValidateCode(6);
        session.setMaxInactiveInterval(5*60); //设置超时时间
        System.out.println(email);
        session.setAttribute("code",integer.toString());
        session.setAttribute("email",email);
        sendMail.sendSimpleMail(integer.toString(),email);
    return Result.success(Code.SAVE_OK,"发送成功！");
    }

//        邮箱注册
    @PostMapping("/register")
    public Result<String> register(@RequestBody User user ,HttpSession session){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getEmail,user.getEmail());
        User one = userService.getOne(wrapper);
        String code = user.getCode();
        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(password);
        String SessionCode =(String) session.getAttribute("code");
        String email =(String) session.getAttribute("email");
        String inputEmail = user.getEmail();
        log.info("session验证码"+SessionCode +"邮箱"+email);
            log.info("输入的验证码" +code);

        if(SessionCode==null){
            return Result.error(Code.SAVE_ERR,"验证码过期或未发送请获取验证码！");
        }
        if(SessionCode.equals(code)&& one ==null && email.equals(inputEmail)){
            //MD5加密用户密码
            userService.save(user);
            return Result.success(Code.SAVE_OK,"注册成功");
        }else if (!SessionCode.equals(code)){
            return  Result.error(Code.SAVE_ERR,"验证码错误！");
        }else {
            return  Result.error(Code.SAVE_ERR,"改邮箱已存在！");
        }
    }
    @PostMapping("login")
    public Result<UserInfo> emailLogin(@RequestBody User user){
        JwtHelper jwt  =new JwtHelper();
        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getEmail,user.getEmail()).eq(User::getPassword,password);
        LambdaQueryWrapper<UserInfo> wrapper1 = new LambdaQueryWrapper<>();
        User tar = userService.getOne(wrapper);
        wrapper1.eq(UserInfo::getInfo_id,tar.getId());
        UserInfo tarInfo = userInfoService.getOne(wrapper1);
        System.out.println(user);
        if(tar==null){
            return Result.error(Code.SAVE_ERR,"用户不存在或密码错误！");
        }else if(tarInfo==null&&tar!=null){
            UserInfo  u = new UserInfo();
            u.setName(tar.getName());
            u.setInfo_id(tar.getId());
            u.setEmail(tar.getEmail());
            String token = jwt.getToken(u.getInfo_id());
            u.setToken(token);
            LocalDateTime localDateTime = LocalDateTime.now();
            u.setRegister_time(localDateTime);
            userInfoService.save(u);
         return Result.success(Code.SAVE_OK,u,"登录成功!");

        }else if(tar!=null&&tarInfo!=null){
            String token = jwt.getToken(tarInfo.getInfo_id());
            tarInfo.setToken(token);
            return Result.success(Code.SAVE_OK,tarInfo,"登录成功！");

        }else {
            return Result.error(Code.SAVE_ERR,"未知错误");
        }

    }

}
