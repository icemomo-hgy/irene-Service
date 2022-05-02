package com.irene.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.lang.reflect.Type;

@Data
@TableName(value = "active_uid")
public class ActivityId {
    @TableId(type = IdType.AUTO)
    private Integer nid;
    private Long uid;
    private  Integer aid;
    @TableField("finish")
    private Integer finish;
}
