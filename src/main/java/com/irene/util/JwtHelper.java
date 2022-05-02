package com.irene.util;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.UUID;

public class JwtHelper {
    private Long tIme  = 1000*60*60*24L;
    private Long expireTime = System.currentTimeMillis()+tIme * 30; //设置过期时间为30天
    String signature = "soe-irene"; //签名
        public  String  getToken(Long id){
            String token = Jwts.builder().setHeaderParam("typ", "JWT")
                    .setHeaderParam("alg", "HS256")
                    .claim("id", id)
                    .setExpiration(new Date(expireTime))
                    .setId(UUID.randomUUID().toString())
                    .signWith(SignatureAlgorithm.HS256, signature).compact();
            return token;
        }

        //根据token 获取用户ID
        public Long getTokenId(String token){
            JwtParser parser = Jwts.parser();
            Jws<Claims> claimsJws = parser.setSigningKey(signature).parseClaimsJws(token);
            Claims body = claimsJws.getBody();
            Long id =(Long) body.get("id");
            return id;
        }

}
