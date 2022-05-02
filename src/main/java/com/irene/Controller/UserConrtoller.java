package com.irene.Controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.irene.Entity.Org;
import com.irene.Entity.User;
import com.irene.Entity.UserInfo;
import com.irene.Excetion.BusinessException;
import com.irene.Mapper.UserMapper;
import com.irene.Service.OrgService;
import com.irene.Service.UserInfoService;
import com.irene.Service.UserService;
import com.irene.common.*;
import com.irene.util.JwtHelper;
import com.irene.util.QiniuUtils;
import com.irene.util.StringUtils;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UserConrtoller {
    @Autowired
    private UserService userService;
    @Autowired
    private OrgService orgService;
    @Autowired
    private SendMail sendMail;
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private QiniuUtils qiniuUtils;
    @SneakyThrows
    //发送邮箱验证码
    @PostMapping("/sendEmail")
    public Result<String> sendEmail( String email, HttpSession session){
        Integer integer = ValidateCodeUtils.generateValidateCode(6);
        session.setMaxInactiveInterval(5*60); //设置超时时间
        System.out.println(email);
        session.setAttribute("code",integer.toString());
        session.setAttribute("email",email);
        sendMail.sendSimpleMail(integer.toString(),email, MailModel.REGISTER_MAIL);
    return Result.success(Code.SAVE_OK,"发送成功！");
    }

//        邮箱注册
    @PostMapping("/register")
    public Result<String> register(User user ,HttpSession session){
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
        }else if(one ==null){
            return  Result.error(Code.SAVE_ERR,"改邮箱已存在！");
        }else {
            return Result.error(Code.SAVE_ERR,"未知错误");
        }
    }
    /**
     * @author 黄冠瑛
     * @date 2022/5/3
     * 邮箱登录
     */
    @PostMapping("/login")
    public Result<UserInfo> emailLogin(User user){
        JwtHelper jwt  =new JwtHelper();
        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getEmail,user.getEmail()).eq(User::getPassword,password);
        LambdaQueryWrapper<UserInfo> wrapper1 = new LambdaQueryWrapper<>();
        User tar = userService.getOne(wrapper);
        wrapper1.eq(UserInfo::getInfoId,tar.getId());
        UserInfo tarInfo = userInfoService.getOne(wrapper1);

        if(tar==null){
            return Result.error(Code.SAVE_ERR,"用户不存在或密码错误！");
        }

     if(tarInfo==null&&tar!=null){
            UserInfo  u = new UserInfo();
            u.setName(tar.getName());
            u.setInfoId(tar.getId());
            u.setEmail(tar.getEmail());
            String token = jwt.getToken(u.getInfoId());
            u.setToken(token);
            userInfoService.save(u);
         return Result.success(Code.SAVE_OK,u,"登录成功!");

        }else if(tar!=null&&tarInfo!=null){
         Integer oid = tarInfo.getOid();
         Org org = orgService.getById(oid);
            System.out.println(tarInfo);
            String token = jwt.getToken(tarInfo.getInfoId());
            tarInfo.setToken(token);
            tarInfo.setOrg(org);
         userInfoService.updateById(tarInfo);
            return Result.success(Code.SAVE_OK,tarInfo,"登录成功！");

        }else {
            return Result.error(Code.SAVE_ERR,"未知错误");
        }

    }

    /**
     * @author 黄冠瑛
     * @date 2022/5/3
     * 重置密码验证邮箱
     */
    @SneakyThrows
    @PostMapping("/sendresetpsd")
    public Result  sendresetpsd(User user,HttpSession session){
        Integer code = ValidateCodeUtils.generateValidateCode(6);
        session.setAttribute("Rcode",code);
        session.setAttribute("Remail",user.getEmail().toString());
        session.setMaxInactiveInterval(5*60); //五分钟过期
       sendMail.sendSimpleMail(code.toString(),user.getEmail().toString(),MailModel.RESETPSD_MAIL);
        return Result.success(Code.SAVE_OK,"邮箱发送成功！");
    }



    /**
     * @author 黄冠瑛
     * @date 2022/5/3
     * 邮箱重置密码
     */
    @PostMapping("/resetpsd")
    public Result resetpsd(User user,HttpSession session){
        String email = user.getEmail();
        String inputCode = user.getCode();
        String code = session.getAttribute("Rcode").toString();
        String sessionEmail = session.getAttribute("Remail").toString();
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getEmail,email);
        User tar = userService.getOne(wrapper);
        if(email.equals(sessionEmail) && inputCode.equals(code)){
            String s = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
            tar.setPassword(s);
            userService.updateById(tar);
            return Result.success(Code.SAVE_OK,"修改密码成功！");
        }else if(code==null|| !inputCode.equals(code)){
           return Result.error(Code.SAVE_ERR,"验证码错误！");
        }else {
            throw new BusinessException(Code.BUSINESS_ERR,"未知异常");
        }

    }


    @SneakyThrows
    @PostMapping("/avatar/upload")
    public Result<String> uploadAvatar(String token,MultipartFile file){
        Long tokenId = new JwtHelper().getTokenId(token);
        LambdaQueryWrapper<UserInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserInfo::getInfoId,tokenId);
        UserInfo tar = userInfoService.getOne(wrapper);
        System.out.println(tar);
        String imgName = StringUtils.getImgName(file.getOriginalFilename());

            if(tar!=null&&tar.getPic()==null){
                log.info("直接上传！");
                String resName = qiniuUtils.upload((FileInputStream) file.getInputStream(),imgName);

                tar.setPic(resName);
                    userInfoService.updateById(tar);
                return Result.success(Code.SAVE_OK,resName,"上传成功");
            }else if(tar!=null&&tar.getPic()!=null){
                log.info("覆盖上传！");
                String[] split = tar.getPic().split("https://soe.icemomo.com/");
                String resName = qiniuUtils.reupload((FileInputStream) file.getInputStream(), imgName,split[1]);
                 tar.setPic(resName);
                userInfoService.updateById(tar);
                return Result.success(Code.SAVE_OK,resName,"覆盖上传");
            }
            else {
                throw new BusinessException(Code.BUSINESS_ERR,"用户签名已过期");
            }

        }


//        更新用户信息
    @PostMapping("/updateUser")
    public Result<UserInfo> updateUserInfo(UserInfo userInfo){
            if(userInfo == null){
                throw new BusinessException(Code.BUSINESS_ERR,"不能传入空参");
            }else {
              try {
                  Long tokenId = new JwtHelper().getTokenId(userInfo.getToken());
                  userInfo.setInfoId(tokenId);
                  userInfoService.updateById(userInfo);
                  return Result.success(Code.SAVE_OK,"修改用户信息成功！");
              }catch (Exception e){
                  throw new BusinessException(Code.BUSINESS_ERR,"Token错误过期，请重新登录！");
              }

            }

    }


}
