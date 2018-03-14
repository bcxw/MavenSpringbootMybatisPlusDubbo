package com.zee.common;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录页面跳转过滤器
 */

@Component
@ServletComponentScan
@WebFilter(filterName = "loginFilter", urlPatterns = {"/index.html","/"})
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Object uo=request.getSession().getAttribute("user");
        String loginHtml="/login.html";
        if(uo==null){
            response.sendRedirect(loginHtml);
        }else{
            filterChain.doFilter(request,response);
        }

    }

    @Override
    public void destroy() {

    }

}
