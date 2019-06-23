package com.security;

import com.model.JwtAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

    public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    //设定filter 要辨别的路径，
    public JwtAuthenticationTokenFilter() {
        super("/rest/**");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {

        //这里是返回http head 里面存放 key"Authorisation"  对应的token
        String header = httpServletRequest.getHeader("Auth");

        //在前段生成header的时候认为加入一个标记，这里加的是Token + space
        if (header == null || !header.startsWith("flag ")) {
            throw new RuntimeException("JWT Token is missing");
        }

        String authenticationToken = header.substring(5);

        JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);
        return getAuthenticationManager().authenticate(token);
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}
