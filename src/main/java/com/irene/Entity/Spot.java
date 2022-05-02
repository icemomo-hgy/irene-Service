package com.irene.Entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
@TableName(value = "spot")
public class Spot {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String location;
    private Integer current;
    private String service_object;
    private  String context;
    private String type;
    @TableField(value = " create_time",fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
    private LocalDate createTime;
    private String pic_src;

}
