package com.mayblackcat.helloblog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mayblackcat.helloblog.entity.BlogTag;
import com.mayblackcat.helloblog.entity.BlogType;
import com.mayblackcat.helloblog.service.impl.AdminTagServiceImpl;
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
public class AdminTagController {

    @Autowired
    private AdminTagServiceImpl adminTagService;

    //分页
    @GetMapping("/tags")
    public String tags(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum, Model model){


        //设置分页大小
        PageHelper.startPage(pageNum,5);
        List<BlogTag> allTag=adminTagService.getListTags();
        //获取分页结果并设置到视图中
        PageInfo<BlogTag> res=new PageInfo<>(allTag);
        model.addAttribute("page",res);

        return "admin/tags";
    }

    //新增
    @GetMapping("/tags/add")
    public String addTags(Model model){
        model.addAttribute("tag",new BlogTag());
        return "admin/tags-input";
    }

    //提交
    @PostMapping(value = "/tags",produces = "application/json;charset=utf-8")
    public String submitTags(@Valid BlogTag tag, BindingResult result, RedirectAttributes attributes){
        if(adminTagService.getTagByName(tag.getName())!=null){
            result.rejectValue("name", "nameError", "该标签已经存在，不能添加重复的标签 ！");
        }
        if(result.hasErrors()){
            return "redirect:/admin/tags/add";
        }

        if(adminTagService.addTags(tag)>0){
            attributes.addFlashAttribute("message", "添加标签成功！");
        }else {
            attributes.addFlashAttribute("message", "添加失败！");
        }
        return "redirect:/admin/tags";
    }

    //跳转到修改页面，将前端id值传入后台进行查询，显示对应id值内容
    @GetMapping(value = "/tags/{id}/input",produces = "application/json;charset=utf-8")
    public String modifyPage(@PathVariable Long id,Model model){
        model.addAttribute("tag",adminTagService.selectTags(id));

        return "admin/tags-input";
    }

    //标签修改
    @PostMapping(value = "/tags/{id}",produces = "application/json;charset=utf-8")
    public String modifyTags(@Valid BlogTag tag,@PathVariable Long id, BindingResult result, RedirectAttributes attributes){
        if (adminTagService.getTagByName(tag.getName())!=null){
            result.rejectValue("name","nameError","该分类已经存在，无法修改！");
        }
        if (result.hasErrors()){

            return "admin/tags-input";
        }
        int bt=adminTagService.modifyTags(tag);
        if (bt>0){
            attributes.addFlashAttribute("message","修改分类成功！");

        }else {
            attributes.addFlashAttribute("message","修改失败！");
        }

        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String deleteTags(@PathVariable Long id,RedirectAttributes attributes){
        Boolean flag=adminTagService.deleteTags(id);
        if (flag){
            attributes.addFlashAttribute("message","删除成功！");
        }else {
            attributes.addFlashAttribute("message","删除失败！");
        }
        return "redirect:/admin/tags";
    }

}
