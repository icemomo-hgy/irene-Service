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
 * @Date: 2022/5/7 13:45
 * @Description:
 * @since JDK 1.8
 */
@TableName("community_img")
@Data
public class CommunityImg {
    @TableId(type = IdType.AUTO)
    private Integer comImgId;

    private Long ids;
    private String url;

}
