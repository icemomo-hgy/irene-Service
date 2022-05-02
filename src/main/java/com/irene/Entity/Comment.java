package com.irene.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("comment")
public class Comment {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("user_id")
    private Long userId;
    private Integer cid;
    private Integer likes;
    private String context;
    @TableField(exist = false)
    private String nickname;

}
