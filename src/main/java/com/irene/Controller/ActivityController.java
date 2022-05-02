package com.irene.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.irene.Entity.Activity;
import com.irene.Entity.ActivityId;
import com.irene.Entity.Org;
import com.irene.Entity.Spot;
import com.irene.Mapper.ActivityIdMapper;
import com.irene.Service.*;
import com.irene.VO.ActivityDetail;
import com.irene.common.Code;
import com.irene.common.Result;
import com.irene.util.JwtHelper;
import com.irene.util.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @BelongsProject: irene
 * @BelongsPackage: com.irene.Controller
 * @Auther: i.gy@outlook.com
 * @Date: 2022/5/5 16:50
 * @Description:
 * @since JDK 1.8
 */
@RestController
@Slf4j
public class ActivityController {
    @Autowired
    private SpotService spotService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private ActivityIdService activityIdService;
    @Autowired
    private ActivityIdMapper activityIdMapper;
    @Autowired
    private OrgService orgService;
    //获取所有志愿点
    @GetMapping("/getAllActivity")
    public Result<List<Spot>> getAllActivity(){
        List<Spot> list = spotService.list();
        return Result.success(Code.GET_OK,list,"查询成功！");

    }

    //获取未结束的活动详情
    @GetMapping("/getService")
    public Result getService(@RequestParam(value = "page" ,required = false,defaultValue = "1")Integer page
    ,@RequestParam(value = "size",required = false,defaultValue = "20") Integer size){
        Page<Activity> noExpire = activityService.findNoExpire(LocalDateTime.now(),page,size);
        return Result.success(Code.GET_OK,noExpire,"查询成功！");
    }
    //获取已过期志愿活动
    @GetMapping("/getOldService")
    public Result getOldService(@RequestParam(value = "page" ,required = false,defaultValue = "1")Integer page
            ,@RequestParam(value = "size",required = false,defaultValue = "20") Integer size){
        Page<Activity> expire = activityService.findExpire(LocalDateTime.now(),page,size);

        return Result.success(Code.GET_OK,expire,"查询成功");
    }
    //用户报名志愿活动
    @PostMapping("/joinService")
    public Result joinService(Integer id,String token){
        if(id==null){
            return Result.error(Code.SAVE_ERR,"Id不能为空！");
        }
        Long tokenId = new JwtHelper().getTokenId(token);
        if(userInfoService.getById(tokenId).getOid()==null){
            return Result.error(Code.SAVE_ERR,"你还没有组织，请先加入一个组织");
        }

        LocalDateTime endTime = activityService.getById(id).getEndTime();
        if(endTime.isBefore(LocalDateTime.now())){
            return Result.error(Code.SAVE_ERR,"活动已过期");
        }
        ActivityId activityId = new ActivityId();
        activityId.setUid(tokenId);
        activityId.setAid(id);
        LambdaQueryWrapper<ActivityId> wrapper = new LambdaQueryWrapper();
        wrapper.eq(ActivityId::getAid,id).eq(ActivityId::getUid,tokenId);
        if(activityIdService.getOne(wrapper)!=null){
            return Result.error(Code.SAVE_ERR,"你已经报名过了！");
        }
        activityIdService.save(activityId);
        return Result.success(Code.SAVE_OK,"报名成功！");
    }

    //用户取消报名
    @PostMapping("/outService")
    public Result outService(Integer id,String token){
        if(id==null){
            return Result.error(Code.SAVE_ERR,"Id为空！");
        }
        Long tokenId = new JwtHelper().getTokenId(token);
        LambdaQueryWrapper<ActivityId> wrapper = new LambdaQueryWrapper();
        wrapper.eq(ActivityId::getAid,id).eq(ActivityId::getUid,tokenId);
        ActivityId one = activityIdService.getOne(wrapper);
        if(one==null){
            return Result.error(Code.SAVE_ERR,"你没有参加该次活动！");
        }
        boolean b = activityIdService.removeById(one.getNid());
        return Result.success(Code.SAVE_OK,"取消成功！");


    }

    //查看用户历史参加的活动记录 (分页)
    @PostMapping("/getServiceHistory")
    public Result getServiceHistory(String token,@RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
                                    @RequestParam(value = "size",required = false,defaultValue = "10") Integer size){
        Long tokenId = new JwtHelper().getTokenId(token);
        List<ActivityDetail> servicesHistory = activityIdMapper.getServicesHistory(tokenId);
        List<ActivityDetail> collect = servicesHistory.stream().map((item) -> {
            Spot spot = spotService.getById(item.getLocation());
            item.setLocationName(spot.getLocation());
            Org org = orgService.getById(item.getOrgId());
            item.setOrgName(org.getName());
            if(item.getEndTime().isBefore(LocalDateTime.now())){
                item.setStatus("已完成");
            }else {
                item.setStatus("进行中");
            }

            return item;
        }).collect(Collectors.toList());
        Page page1 = PageUtils.getPage(page, size, collect);

        return Result.success(Code.GET_OK,page1,"查询成功！");
    }



}
