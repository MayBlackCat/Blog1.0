package com.mayblackcat.helloblog.entity;


import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@ToString
public class BlogComment {

    private Long id;
    private String username;
    private String email;
    private String content;
    private String avatar;  //头像
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private Long blogId;    //  博客ID
    private Long parentId;  //  父评论ID
    private Long sonId;     //  子评论ID

}
