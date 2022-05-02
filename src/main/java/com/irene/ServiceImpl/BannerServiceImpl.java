package com.irene.ServiceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.irene.Entity.Banner;
import com.irene.Mapper.BannerMapper;
import com.irene.Service.BannerService;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: irene
 * @BelongsPackage: com.irene.ServiceImpl
 * @Auther: i.gy@outlook.com
 * @Date: 2022/5/8 17:26
 * @Description:
 * @since JDK 1.8
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {
}
