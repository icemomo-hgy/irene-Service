package com.irene.ServiceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.irene.Entity.ActivityImg;
import com.irene.Mapper.ActivityImgMapper;
import com.irene.Service.ActivityImgService;
import org.springframework.stereotype.Service;

@Service
public class ActivityImgImpl extends ServiceImpl<ActivityImgMapper, ActivityImg> implements ActivityImgService {
}
