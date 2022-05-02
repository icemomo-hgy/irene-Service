package com.irene.ServiceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.irene.Entity.Spot;
import com.irene.Mapper.SpotMapper;
import com.irene.Service.SpotService;
import org.springframework.stereotype.Service;

@Service
public class SpotImpl extends ServiceImpl<SpotMapper, Spot> implements SpotService {
}
