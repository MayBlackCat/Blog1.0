package com.mayblackcat.helloblog.controller.admin;

import com.mayblackcat.helloblog.entity.BlogUser;
import com.mayblackcat.helloblog.service.impl.AdminServiceImpl;
import com.mayblackcat.helloblog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@SuppressWarnings("unchecked")
@RequestMapping(value = "/admin",produces = "application/json;charset=utf-8")
public class AdminPwdController {

    @Autowired
    private AdminServiceImpl adminService;

    @GetMapping("/pwd")
    public String pwd(){
        return "admin/modifypassword";
    }

    //修改密码
    @PostMapping("/modify")
    public ModelAndView modify(@RequestParam String username,
                               @RequestParam String oldPassword,
                               @RequestParam String newPassword,
                               HttpSession session,
                               RedirectAttributes attributes){

            ModelAndView mav=new ModelAndView();

            //判断密码是否为空
            if (username==null||newPassword==null){
                attributes.addFlashAttribute("message","用户名密码不能为空！");
                mav.setViewName("redirect:/admin/pwd");
                return mav;
            }
            //验证用户名和密码是否正确
            BlogUser user=adminService.adminService(username,oldPassword);
            if (user!=null){
                user.setPassword(MD5Utils.code(newPassword));
                adminService.updatePassword(user);
                user.setPassword(null);
                session.setAttribute("user",user);

                mav.setViewName("admin/index");
            }else {
                attributes.addFlashAttribute("message","用户名或者密码错误！");
                mav.setViewName("redirect:/admin");
            }
        return mav;


    }
}
