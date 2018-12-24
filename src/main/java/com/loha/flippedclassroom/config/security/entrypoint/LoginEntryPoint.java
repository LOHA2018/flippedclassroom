package com.loha.flippedclassroom.config.security.entrypoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 获取不同的登录界面
 *
 * @author zhoujian
 * @date 2018/12/15
 */
@Component
@Slf4j
public class LoginEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        String prefix1 = "/studentpc";
        String prefix2 = "/teacherpc";
        String url = httpServletRequest.getRequestURI();
        if(isAjaxRequest(httpServletRequest)){
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,e.getMessage());
        }
        else if(url.startsWith(prefix1)||url.startsWith(prefix2)){
            httpServletResponse.sendRedirect("/pc/login");
        }
        else {
            httpServletResponse.sendRedirect("/login");
        }
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String ajaxFlag = request.getHeader("X-Requested-With");
        return ajaxFlag != null && "XMLHttpRequest".equals(ajaxFlag);
    }
}
