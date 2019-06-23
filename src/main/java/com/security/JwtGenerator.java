package com.security;

import com.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
//create a token by user information
public class JwtGenerator {

    //return a new token
    public String generate(JwtUser jwtUser) {
        //create token' claims 保存在token payload里面
        //claim 是map格式
        //在claims private的部分可以加入 {'name':'jone', 'admin':true} 这是在登录成功后加入进去的
        //把id，username， role 信息都装进去了
        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getUserName());
        claims.put("userId", String.valueOf(jwtUser.getId()));
        claims.put("role", jwtUser.getRole());

        //生成jwt, 加入最基本的claims 和签名signWith
        //有很多选择，可以具体设置jwt， 这里的youtube 是秘钥可以自己修改
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "youtube")
                .compact();
    }
}
