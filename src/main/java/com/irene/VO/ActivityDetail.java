package com.irene.VO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.irene.Entity.Activity;
import com.irene.Entity.Org;
import com.irene.Entity.Spot;
import lombok.Data;

/**
 * @BelongsProject: irene
 * @BelongsPackage: com.irene.VO
 * @Auther: i.gy@outlook.com
 * @Date: 2022/5/5 21:17
 * @Description:
 * @since JDK 1.8
 *
 * 放回活动的详细信息
 */
@Data
public class ActivityDetail extends Activity {
    @TableField(exist = false)
    private String status;

}
