package com.irene.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "posts_img")
public class PostsImg {

    private Integer id;
    private String img_url;
}
