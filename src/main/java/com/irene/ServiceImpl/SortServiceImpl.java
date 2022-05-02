package com.irene.ServiceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.irene.Entity.Sort;
import com.irene.Mapper.SortMapper;
import com.irene.Service.SortService;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: irene
 * @BelongsPackage: com.irene.ServiceImpl
 * @Auther: i.gy@outlook.com
 * @Date: 2022/5/8 14:06
 * @Description:
 * @since JDK 1.8
 */
@Service
public class SortServiceImpl extends ServiceImpl<SortMapper, Sort> implements SortService {
}
