package com.loha.flippedclassroom.config.security.handler;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 身份验证失败
 *
 * @author zhoujian
 * @date 2018/12/15
 */
@Component
@Slf4j
public class IdentityCheckSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(authentication.getAuthorities().toString());
        writer.close();
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
    }
}
