package com.irene.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @BelongsProject: irene
 * @BelongsPackage: com.irene.Entity
 * @Auther: i.gy@outlook.com
 * @Date: 2022/5/8 17:19
 * @Description:
 * @since JDK 1.8
 */
@Data
@TableName("banner")
public class Banner {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String url;
    private String context;

}
