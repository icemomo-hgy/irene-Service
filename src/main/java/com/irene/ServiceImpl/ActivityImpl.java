package com.irene.ServiceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.irene.Entity.Activity;
import com.irene.Entity.Org;
import com.irene.Entity.Spot;
import com.irene.Mapper.ActivityMapper;
import com.irene.Service.ActivityService;
import com.irene.Service.OrgService;
import com.irene.Service.SpotService;
import com.irene.VO.ActivityDetail;
import com.irene.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public  class ActivityImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {
    @Autowired
    private OrgService orgService;
    @Autowired
    private SpotService spotService;

    public Page<Activity> findNoExpire(LocalDateTime time,Integer page,Integer size){

        //合并信息后并返回
        LambdaQueryWrapper<Activity> wrapper = new LambdaQueryWrapper<>();
        wrapper.ge(Activity::getEndTime,time);
        List<Activity> list = super.list(wrapper);
        List<Activity> collect = list.stream().map((item) -> {
            item.setOrgName(orgService.getById(item.getOrgId()).getName());
            item.setLocationName(spotService.getById(item.getLocation()).getLocation());
            return item;
        }).collect(Collectors.toList());
        Page page1 = PageUtils.getPage(page,size,collect);

        return page1;
    }

    @Override
    public  Page<Activity> findExpire(LocalDateTime time,Integer page,Integer size) {
        LambdaQueryWrapper<Activity> wrapper = new LambdaQueryWrapper<>();
        wrapper.lt(Activity::getEndTime,time);
        List<Activity> list = super.list(wrapper);
        List<Activity> collect = list.stream().map((item) -> {
            item.setOrgName(orgService.getById(item.getOrgId()).getName());
            item.setLocationName(spotService.getById(item.getLocation()).getLocation());
            return item;
        }).collect(Collectors.toList());
        Page page1 = PageUtils.getPage(page, size,collect);

        return page1;
    }
}
