package com.irene.ServiceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.irene.Entity.CommunityImg;
import com.irene.Mapper.CommunityImgMapper;
import com.irene.Service.CommunityImgService;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: irene
 * @BelongsPackage: com.irene.ServiceImpl
 * @Auther: i.gy@outlook.com
 * @Date: 2022/5/7 13:52
 * @Description:
 * @since JDK 1.8
 */
@Service
public class CommunityImgImpl extends ServiceImpl<CommunityImgMapper, CommunityImg> implements CommunityImgService {
}
