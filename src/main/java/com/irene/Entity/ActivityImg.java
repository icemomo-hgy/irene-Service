package com.irene.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("activity_img")
public class ActivityImg {
    private Integer id;
    private String src;
}
