package com.mayblackcat.helloblog.entity;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public class Blog implements Serializable {
    private static final long serialVersionUID=1L;

    //标识主键
    private Long id;
    //博客标题
    private String title;
    //博客内容
    private String content;
    //博客图片
    private String blogPicture;
    //博客评论
    private Boolean commentable;
    //博客赞赏
    private Boolean appreciate;
    //博客版权
    private Boolean publish;
    //草稿
    private Boolean published;
    //文章推荐
    private Boolean recommend;
    //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //更新时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    //博客标识
    private String flags;
    //博客浏览
    private Integer views;

    //标签值集合
    private String tagIds;

    //文章描述
    private String description;

    private Long userId;    //设置用户索引


    private Long typeId;    //设置分类索引
    private String tagsName;  //设置多个标签索引
    private String TypeName;

    private BlogType type;
    private List<BlogTag> tags;
    private BlogUser user;
    private  String typeName;

    private List<BlogComment> comments;



    public void init() {
        this.tagIds = tagsToIds(this.getTags());
    }

    private String tagsToIds(List<BlogTag> tags) {
        if (tags!=null) {
            StringBuffer ids = new
                    StringBuffer();
            boolean flag = false;
            for (BlogTag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return tagIds;
        }
    }

}
