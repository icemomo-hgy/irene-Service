package com.irene.Entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName("org")
public class Org {
    private Integer id;
    private String name;
    @TableField(fill = FieldFill.INSERT) //插入时填充字段
    private LocalDateTime create_time;
    private Integer sum_people;
    private Double sum_time;
    private String service_object ;
    private String region;
}
