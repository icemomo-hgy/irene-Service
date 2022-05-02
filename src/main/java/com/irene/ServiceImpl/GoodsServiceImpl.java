package com.irene.ServiceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.irene.Entity.Goods;
import com.irene.Mapper.GoodsMapper;
import com.irene.Service.GoodsService;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: irene
 * @BelongsPackage: com.irene.ServiceImpl
 * @Auther: i.gy@outlook.com
 * @Date: 2022/5/8 14:20
 * @Description:
 * @since JDK 1.8
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

}
