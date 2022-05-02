package com.irene.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.irene.Entity.Activity;
import com.irene.VO.ActivityDetail;

import java.time.LocalDateTime;
import java.util.List;

public interface ActivityService extends IService<Activity> {
    Page<Activity>findNoExpire(LocalDateTime time, Integer page, Integer size);
    Page<Activity> findExpire( LocalDateTime time,Integer page, Integer size);

}
