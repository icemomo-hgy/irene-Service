package com.irene.Entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName("org")
public class Org {
    @TableId(type = IdType.AUTO)
    private Integer oid;
    private String name;
    @TableField(value ="create_time", fill = FieldFill.INSERT) //插入时填充字段
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate createTime;
    @TableField("sum_people")
    private Integer sumPeople;
    @TableField("sum_time")
    private Double sumTime;
    @TableField("service_object")
    private String serviceObject ;
    private String region;
}
