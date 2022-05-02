package com.irene.Entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName(value = "activity")
public class Activity {
    private int id;
    private String name;
    @TableField(fill = FieldFill.INSERT)
    private Date date; //发布日期
    private double time; //服务时长
    private Long user_id;
    private Integer location;
    private String info;
    private int org_id;
    private int people_num;
    private LocalDateTime end_time;

}
