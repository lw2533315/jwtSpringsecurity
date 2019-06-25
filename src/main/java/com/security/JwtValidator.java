package com.security;


import com.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

//检测秘钥，如果匹配将token claim里传来的登录信息存放在JwtUser里
@Component
public class JwtValidator {


    private String secret = "youtube";

    public JwtUser validate(String token) {

        JwtUser jwtUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)    //通过秘钥提取claim body
                    .parseClaimsJws(token)
                    .getBody();
	    
	    //判断是否过期
            if (new Date().after(body.getExpiration())){
                return null;
            }
	    jwtUser = new JwtUser();

            jwtUser.setUserName(body.getSubject());
            jwtUser.setId(Long.parseLong((String) body.get("userId")));
            jwtUser.setRole((String) body.get("role"));
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return jwtUser;
    }
}
