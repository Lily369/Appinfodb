package com.tools;

import com.entiey.BackendUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 功能描述：后台管理拦截器
 *
 * @ClassName: BackendLoginInterceptor
 * @Author: Lily.
 * @Date: 2019/8/8 18:29
 * @Version: V1.0
 */
public class BackendLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //获取Session
        HttpSession session = httpServletRequest.getSession();
        BackendUser backendUser = (BackendUser) session.getAttribute("userSession");
        if (backendUser != null) {
            return true;
        } else {
            httpServletResponse.sendRedirect("/manager1/login");
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
