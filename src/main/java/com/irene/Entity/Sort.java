package com.irene.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @BelongsProject: irene
 * @BelongsPackage: com.irene.Entity
 * @Auther: i.gy@outlook.com
 * @Date: 2022/5/8 14:03
 * @Description:
 * @since JDK 1.8
 */
@Data
public class Sort implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    @TableField("catalogue_id")
    private Long catalogueId;
}
