package com.irene.ServiceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.irene.Entity.Activity;
import com.irene.Entity.ActivityId;
import com.irene.Mapper.ActivityIdMapper;
import com.irene.Mapper.ActivityMapper;
import com.irene.Service.ActivityIdService;
import com.irene.Service.ActivityService;
import org.springframework.stereotype.Service;

@Service
public class ActivityIdImpl extends ServiceImpl<ActivityIdMapper, ActivityId> implements ActivityIdService {
}
