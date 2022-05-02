package com.irene.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "active_id")
public class ActivityId {
    private Integer nid;
    private Long uid;
    private  Integer aid;
}
