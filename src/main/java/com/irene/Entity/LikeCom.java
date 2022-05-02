package com.irene.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @BelongsProject: irene
 * @BelongsPackage: com.irene.Entity
 * @Auther: i.gy@outlook.com
 * @Date: 2022/5/7 20:29
 * @Description:
 * @since JDK 1.8
 */
@TableName("like_com")
@Data
public class LikeCom {
    @TableId(type = IdType.AUTO)
    private Integer likeId;
    private Long uid;
    @TableField("Com_id")
    private Integer comId;
}
