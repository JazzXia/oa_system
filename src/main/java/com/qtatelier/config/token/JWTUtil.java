package com.qtatelier.config.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.qtatelier.dto.BlogUser;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author:JazzXia(kima_riiiiii)
 * @date:2019-12-16 14:49
 * @contact:jazzxiaw@qq.com & xia.weiwei163@163.com
 */
@Component("JWTUtilinfo")
public class JWTUtil extends com.qtatelier.dev_util.tool.token.JWTUtil {

    //服务端秘钥
    private static final String SECERT="QKSBFMSKIQWEDFF_dsa465";

    //令牌时间(5小时)
    private static final int EXPSECOND = 60*60*5*1000;

    //在一个月内可以刷新令牌
    private static final long REFRESHSECOND = 60*60*24*15*1000L;


    //生成秘钥
    private static Key getKey(){
        //HS256加密
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //秘钥
        byte[] apiKeySecertBytes = DatatypeConverter.parseBase64Binary(SECERT);

        Key signingKey = new SecretKeySpec(apiKeySecertBytes,signatureAlgorithm.getJcaName());

        return signingKey;
    }



    /**
     * 生产access_token
     * @param user
     * @return
     */
    @Override
    public String getToken(BlogUser user) {
        /**
          * 添加参数withClaim,设置过期时间withExpiresAt.
          * 使用个人密码作为密钥
          */
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        Date date = new Date(System.currentTimeMillis() + 1000 * 60 * 2 * 60);
        String token = JWT.create().withHeader(map)//header
                .withAudience(user.getUserId())
                .withClaim("Admin", "JazzXia")
                .withExpiresAt(date)
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;


/*        JwtBuilder builder = Jwts.builder().setHeaderParam("typ","JWT")
                .claim("userid",user.getUserId())
                .claim("admin","JazzXia")
                .signWith(SignatureAlgorithm.HS256, getKey());
            //设置过期时间
        if(EXPSECOND >= 0){
            Long now = System.currentTimeMillis();
            Long expMills = now + EXPSECOND;
            System.out.println("到期时间:"+ new Date(expMills));
            Date expDate = new Date(expMills);
            builder.setExpiration(expDate).setNotBefore(new Date());
        }
        String userToken = builder.compact();

        String sign = userToken.split("\\.")[2];
        //在一定时间内刷新令牌
        Long now = System.currentTimeMillis();
        Long ExpMills = now + REFRESHSECOND;
        //将token保存在数据库中
        return userToken;*/
    }


}
