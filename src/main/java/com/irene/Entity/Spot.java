package com.irene.Entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;

@Data
@TableName(value = "spot")
public class Spot {
    private Integer id;
    private String name;
    private String location;
    private Integer current;
    private String service_object;
    private  String context;
    private String type;
    @TableField(fill = FieldFill.INSERT)
    private LocalDate create_time;
    private String pic_src;

}
