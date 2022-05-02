package com.irene.Entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName(value = "user_info")
public class UserInfo {
    private Long info_id;
    private Integer phone;
    private String name;  //user表共有
    private String nickname;
    private LocalDateTime service_time; //服务时长
    private String email; //user表共有
    private String pic;
    private LocalDateTime register_time;
    private String profession;
    private String identity;
    private Integer oid;
    @TableField(exist = false)
    private String token;

}
