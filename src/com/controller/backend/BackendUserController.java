package com.controller.backend;

import com.entiey.BackendUser;
import com.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 功能描述：后台管理用户控制层
 *
 * @ClassName: BackendUserController
 * @Author: Lily.
 * @Date: 2019/8/3 8:33
 * @Version: V1.01
 */
@Controller
@RequestMapping("/manager1")
public class BackendUserController {

    @Resource(name = "backendUserService")
    BackendUserService backendUserService;

    /**
     * 跳转到后台管理登录页面
     *
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "/backendlogin";
    }

    /**
     * 登录
     *
     * @param userCode
     * @param userPassword
     * @param session
     * @return
     */
    @RequestMapping("/dologin")
    public String dologin(String userCode, String userPassword, HttpSession session, HttpServletRequest request) {
        BackendUser backendUser = backendUserService.getByUserCodeAndUserPassword(userCode, userPassword);
        if (backendUser != null) {
            session.setAttribute("userSession", backendUser);
            return "backend/main";
        }
        request.setAttribute("error", "账号或密码错误!");
        return "/backendlogin";
    }

    /**
     * 返回首页
     *
     * @return
     */
    @RequestMapping("/backend/main")
    public String main() {
        return "backend/main";
    }

    /**
     * 注销
     *
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "backendlogin";
    }

}
