package com.irene.Entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName(value = "activity")
public class Activity implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Integer ActivityId;
    private String name;
    @TableField("need_num")
    private Integer needNum;
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date date; //发布日期
    private double time; //服务时长
    private Integer location;
    private String info;
    @TableField("org_id")
    private int orgId;
    private int people_num;
    @TableField("end_time")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private LocalDateTime endTime;
    @TableField(exist = false)
    private String locationName;
    @TableField(exist = false)
    private String orgName;
    private Long mid;

}
