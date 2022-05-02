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
 * @Date: 2022/5/8 14:16
 * @Description:
 * @since JDK 1.8
 */
@TableName("goods")
@Data
public class Goods {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String url;
    private String title;

    private String describes;
    private Double price;
    private Integer sid;
}
