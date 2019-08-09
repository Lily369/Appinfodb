package com.tools;

import com.entiey.DevUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 功能描述：开发者平台拦截器
 *
 * @ClassName: MyInterceptor
 * @Author: Lily.
 * @Date: 2019/8/8 17:22
 * @Version: V1.0
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //获取Session
        HttpSession session = httpServletRequest.getSession();
        DevUser devUser = (DevUser) session.getAttribute("devUserSession");
        if (devUser != null) {
            return true;
        } else {
            //重定向
            httpServletResponse.sendRedirect("/dev/login");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
