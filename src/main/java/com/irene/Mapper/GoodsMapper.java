package com.irene.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.irene.Entity.Goods;
import org.apache.ibatis.annotations.Mapper;

/**
 * @BelongsProject: irene
 * @BelongsPackage: com.irene.Mapper
 * @Auther: i.gy@outlook.com
 * @Date: 2022/5/8 14:19
 * @Description:
 * @since JDK 1.8
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
}
