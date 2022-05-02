package com.irene.Entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String password;
    private String email;
    private String name;
    @TableLogic(value ="0",delval = "1")
    private Integer deleted;
    @TableField(exist = false)
    private String code;
}
