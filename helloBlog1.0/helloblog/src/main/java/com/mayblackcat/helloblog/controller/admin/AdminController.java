package com.mayblackcat.helloblog.controller.admin;

import com.mayblackcat.helloblog.entity.BlogUser;
import com.mayblackcat.helloblog.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/admin",produces =  "application/json;charset=utf-8")
public class AdminController {
    @Autowired
    private AdminServiceImpl adminService;

    @GetMapping
    public String loginPage(){
        return "admin/login";
    }
    //新建资源
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes)
    {
        //验证用户名和密码是否正确
        BlogUser user=adminService.adminService(username,password);
        if (user!=null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else {
            attributes.addFlashAttribute("message","用户名或者密码错误！");
            return  "redirect:/admin";
        }

    }

    //退出登录
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
