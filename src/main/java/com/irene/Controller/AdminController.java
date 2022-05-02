package com.irene.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.irene.Entity.*;
import com.irene.Mapper.BannerMapper;
import com.irene.Service.*;
import com.irene.common.Code;
import com.irene.common.Result;
import com.irene.common.SnowFlake;
import com.irene.util.JwtHelper;
import com.irene.util.QiniuUtils;
import com.irene.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @BelongsProject: irene
 * @BelongsPackage: com.irene.Controller
 * @Auther: i.gy@outlook.com
 * @Date: 2022/5/8 14:47
 * @Description:
 * @since JDK 1.8
 */
@RestController
@Slf4j
public class AdminController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private ActivityImgService activityImgService;
    @Autowired
    private QiniuUtils qiniuUtils;
    @Autowired
    private ActivityIdService activityIdService;
    @Autowired
    private OrgService orgService;
    @Autowired
    private SpotService spotService;
    @Autowired
    private  SortService sortService;
    @Autowired
    private GoodsService goodsService;
    @Autowired BannerService bannerService;
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/setActivity")
    public Result setActivity(Activity activity, String token, MultipartFile file) throws IOException {
        Long tokenId = new JwtHelper().getTokenId(token);
        if(tokenId!=1522844473903329283l){return Result.error(Code.SAVE_ERR,"你没有权限！");}
        Long next = new SnowFlake(5L).next();
        activity.setMid(next);
        activityService.save(activity);
        String imgName = StringUtils.getImgName(file.getOriginalFilename());
        String upload = qiniuUtils.upload((FileInputStream) file.getInputStream(), imgName);
        ActivityImg activityImg = new ActivityImg();
        activityImg.setId(next);
        activityImg.setSrc(upload);
        activityImgService.save(activityImg);
        return Result.success(Code.SAVE_OK,activity,"发布成功！");
    }


    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/deleteActivity")
    public Result deleteActivity(String token,Integer id){
        Long tokenId = new JwtHelper().getTokenId(token);
        if(tokenId!=1522844473903329283l){return Result.error(Code.SAVE_ERR,"你没有权限！");}
        Activity byId = activityService.getById(id);
        if(byId==null){return Result.error(Code.SAVE_ERR,"该活动不存在！");}
        Long mid = byId.getMid();
        activityImgService.removeById(mid);
        LambdaQueryWrapper<ActivityId> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ActivityId::getAid,id);
        List<ActivityId> list = activityIdService.list(wrapper);
        for (ActivityId activityId:list) {
            activityIdService.removeById(activityId.getNid());
        }
        activityService.removeById(id);
        return Result.success(Code.DELETE_OK,"删除成功！");
    }


    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/createOrg")
    public Result createOrg(Org org,String token){
        Long tokenId = new JwtHelper().getTokenId(token);
        if(tokenId!=1522844473903329283l){return Result.error(Code.SAVE_ERR,"你没有权限！");}
            orgService.save(org);
        return Result.success(Code.SAVE_OK,org,"创建成功！");
    }
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/addSpot")
    public Result addSpot(Spot spot, String token,MultipartFile file) throws IOException {
        Long tokenId = new JwtHelper().getTokenId(token);
        if(tokenId!=1522844473903329283l){return Result.error(Code.SAVE_ERR,"你没有权限！");}
        String imgName = StringUtils.getImgName(file.getOriginalFilename());
        String upload = qiniuUtils.upload((FileInputStream) file.getInputStream(), imgName);
        spot.setPic_src(upload);
        spotService.save(spot);
        return Result.success(Code.SAVE_OK,spot,"创建成功");

    }


    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/deleteSpot")
    public Result deleteSpot(Integer id ,String token){
        Long tokenId = new JwtHelper().getTokenId(token);
        if(tokenId!=1522844473903329283l){return Result.error(Code.SAVE_ERR,"你没有权限！");}
        spotService.removeById(id);

        return Result.success(Code.DELETE_OK,"删除成功");
    }


    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/addSort")
    public Result addSort(Sort sort,String token){
        Long tokenId = new JwtHelper().getTokenId(token);
        if(tokenId!=1522844473903329283l){return Result.error(Code.SAVE_ERR,"你没有权限！");}
        sortService.save(sort);
        return Result.success(Code.SAVE_OK,sort,"新增成功");
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/addGoods")
    public  Result addGoods(String token,Goods goods,MultipartFile files) throws IOException {
        Long tokenId = new JwtHelper().getTokenId(token);
        if(tokenId!=1522844473903329283l){return Result.error(Code.SAVE_ERR,"你没有权限！");}
        String imgName = StringUtils.getImgName(files.getOriginalFilename());
        System.out.println(imgName);
        String upload = qiniuUtils.upload((FileInputStream) files.getInputStream(), imgName);
        goods.setUrl(upload);
        goodsService.save(goods);
        return  Result.success(Code.SAVE_OK,goods,"新增成功");
    }

    @GetMapping("/getbanner")
    public  Result getBanner(){
        List<Banner> list = bannerService.list();

        return Result.success(Code.GET_OK,list,"查询成功！");
    }

}
