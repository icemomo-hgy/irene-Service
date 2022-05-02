package com.irene.Entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName(value = "user_info")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.NONE)
    private Long infoId;
    private Long phone;
    private String name;  //user表共有
    private String nickname;
    @TableField("service_time")
    private Integer serviceTime; //服务时长
    private String email; //user表共有
    private String pic;
    @TableField(value = "register_time",fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime registerTime;
    private String profession;
    private String identity;
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Integer oid;
    @TableField(exist = false)
    private String token;
    @TableField(exist = false)
    private Org org;

}
