package com.mayblackcat.helloblog.controller;

import com.mayblackcat.helloblog.entity.BlogMessage;
import com.mayblackcat.helloblog.entity.BlogUser;
import com.mayblackcat.helloblog.service.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MessageController {

    @Autowired
    private MessageServiceImpl messageService;



    @Value("${message.avatar}")
    private String avatar;

    @GetMapping("/message")
    public String message() {
        return "message";
    }

    //    查询留言
    @GetMapping("/messagecomment")
    public String messages(Model model) {
        List<BlogMessage> messages = messageService.listMessage();
        model.addAttribute("messages", messages);
        return "message :: messageList";
    }

    //    新增留言
    @PostMapping("/message")
    public String post(BlogMessage message, HttpSession session, Model model) {
        BlogUser user = (BlogUser) session.getAttribute("user");
        //设置头像
        if (user != null) {
            message.setAvatar(user.getAvatar());
            message.setAdminMessage(true);
        } else {
            message.setAvatar(avatar);
        }
        if (message.getParentMessage().getId() != null) {
            message.setParentMessageId(message.getParentMessage().getId());
        }
        messageService.saveMessage(message);
        List<BlogMessage> messages = messageService.listMessage();
        model.addAttribute("messages", messages);
        return "message :: messageList";
    }

    //    删除留言
    @GetMapping("/messages/{id}/delete")
    public String delete(@PathVariable Long id){
        messageService.deleteMessage(id);
        return "redirect:/message";
    }

}
