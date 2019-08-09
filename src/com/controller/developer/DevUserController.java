package com.controller.developer;

import com.entiey.DevUser;
import com.service.DevUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 功能描述：开发者用户管理控制层
 *
 * @ClassName: DevUserController
 * @Author: Lily.
 * @Date: 2019/8/3 8:31
 * @Version: V1.0
 */
@Controller
@RequestMapping("/dev")
public class DevUserController {
    @Resource(name = "devUserService")
    DevUserService devUserService;

    /**
     * 跳转到开发者登录页面
     *
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "/devlogin";
    }

    /**
     * 登录
     *
     * @param devCode
     * @param devPassword
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/dologin")
    public String devLogin(String devCode, String devPassword, HttpSession session, Model model, HttpServletRequest request) {
        DevUser devUser = devUserService.getByDevCodeAndDevPassword(devCode, devPassword);
        if (devUser == null) {
            request.setAttribute("error", "账号或密码错误!");
            return "/devlogin";
        }
        session.setAttribute("devUserSession", devUser);
        model.addAttribute("devUser", devUser);
        return "/developer/main";
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
        return "devlogin";
    }

    /**
     * 返回首页
     *
     * @return
     */
    @RequestMapping("/flatform/main")
    public String getHome() {
        return "developer/main";
    }


}
