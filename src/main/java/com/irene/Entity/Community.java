package com.irene.Entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data

public class Community {
    private Integer id;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime publish_time;
    private Integer likes;
    private Integer comment_sum;
    private  Integer img_ids;
    private String title;
    private String context;

}
