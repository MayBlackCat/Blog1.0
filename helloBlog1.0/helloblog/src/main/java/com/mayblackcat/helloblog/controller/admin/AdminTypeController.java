package com.mayblackcat.helloblog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mayblackcat.helloblog.entity.BlogType;
import com.mayblackcat.helloblog.service.impl.AdminTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/admin",produces = "application/json;charset=utf-8")
public class AdminTypeController {

    @Autowired
    private AdminTypeServiceImpl adminTypeService;


    //分页
    @GetMapping("/types")
    public String types(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum, Model model){


        //设置分页大小
        PageHelper.startPage(pageNum,8);
        List<BlogType> allType=adminTypeService.getListType();
        //获取分页结果并设置到视图中
        PageInfo<BlogType> res=new PageInfo<>(allType);
        model.addAttribute("page",res);

        return "admin/types";
    }

    //新增
    @GetMapping("/types/add")
    public String addTypes(Model model){
        model.addAttribute("type",new BlogType());
        return "admin/types-input";
    }

    //提交
    @PostMapping("/types")
    public String submitTypes(@Valid BlogType type, BindingResult result, RedirectAttributes attributes){
        if(adminTypeService.getTypeName(type.getName())!=null){
            result.rejectValue("name", "nameError", "该类型已经存在，不能添加重复的该类型！");
        }
        if(result.hasErrors()){
            return "redirect:/admin/types/add";
        }

        if(adminTypeService.Type(type)>0){
            attributes.addFlashAttribute("message", "添加分类成功！");
        }else {
            attributes.addFlashAttribute("message", "操作失败！");
        }
        return "redirect:/admin/types";
    }

    //跳转到修改页面，将前端id值传入后台进行查询，显示对应id值内容
    @GetMapping("/types/{id}/input")
    public String modifyPage(@PathVariable Long id,Model model){
        model.addAttribute("type",adminTypeService.getType(id));

        return "admin/types-input";
    }

    //分类修改
    @PostMapping("/types/{id}")
    public String modifyTypes(@Valid BlogType type, BindingResult result, RedirectAttributes attributes){

        if (adminTypeService.getTypeName(type.getName())!=null){
            result.rejectValue("name","nameError","该分类已经存在，无法修改！");
        }
        if (result.hasErrors()){
            return "admin/types-input";
        }
        int bt=adminTypeService.updateType(type);
        if (bt>0){
            attributes.addFlashAttribute("message","修改分类成功！");

        }else {
            attributes.addFlashAttribute("message","修改失败！");
        }

        return  "redirect:/admin/types";
    }



    //删除
    @GetMapping("/types/{id}/delete")
    public String deleteTypes(@PathVariable Long id,RedirectAttributes attributes){
        Boolean flag=adminTypeService.deleteType(id);
        if (flag){
            attributes.addFlashAttribute("message","删除成功！");
        }else {
            attributes.addFlashAttribute("message","删除失败！");
        }
        return "redirect:/admin/types";
    }

}
