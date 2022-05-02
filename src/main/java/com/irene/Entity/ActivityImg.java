package com.irene.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("activity_img")
public class ActivityImg {
    @TableId(type = IdType.NONE)
    private Long id;
    private String src;
}
