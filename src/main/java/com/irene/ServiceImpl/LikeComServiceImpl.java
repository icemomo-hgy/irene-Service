package com.irene.ServiceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.irene.Entity.LikeCom;
import com.irene.Mapper.LikeComMapper;
import com.irene.Service.LikeComService;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: irene
 * @BelongsPackage: com.irene.ServiceImpl
 * @Auther: i.gy@outlook.com
 * @Date: 2022/5/7 20:53
 * @Description:
 * @since JDK 1.8
 */
@Service
public class LikeComServiceImpl extends ServiceImpl<LikeComMapper, LikeCom> implements LikeComService {
}
