package com.mayblackcat.helloblog.vo;





import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ToString
public class ShowBlog {
    private Long id;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private String typeName;
    private String blogPicture;
    private String userAvatar;
    private String userName;
    private Integer views;
    private String description;
}
