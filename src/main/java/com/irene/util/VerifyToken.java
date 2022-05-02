package com.irene.util;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.irene.Entity.User;
import com.irene.Entity.UserInfo;
import com.irene.Service.UserInfoService;
import com.irene.Service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: irene
 * @BelongsPackage: com.irene.util
 * @Auther: i.gy@outlook.com
 * @Date: 2022/5/4 20:40
 * @Description:
 * @since JDK 1.8
 *
 * token验证是否有效工具类
 *
 */

public class VerifyToken {
    public static Boolean verifyTokenId(String Token){
        if(Token == null){
            throw new RuntimeException("Token不能为空！");
        }
        try {
            Long tokenId = new JwtHelper().getTokenId(Token);
            return  true;
        }catch (SignatureException |MalformedJwtException | ExpiredJwtException e){
            return false;
        }



    }
}
