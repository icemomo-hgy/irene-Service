package com.irene.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.irene.Entity.Org;
import com.irene.Entity.UserInfo;
import com.irene.Excetion.BusinessException;
import com.irene.Service.OrgService;
import com.irene.Service.UserInfoService;
import com.irene.common.Code;
import com.irene.common.Result;
import com.irene.util.JwtHelper;
import com.irene.util.VerifyToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.ResultSet;
import java.util.List;

/**
 * @BelongsProject: irene
 * @BelongsPackage: com.irene.Controller
 * @Auther: i.gy@outlook.com
 * @Date: 2022/5/4 19:15
 * @Description:
 * @since JDK 1.8
 */
@RestController
@Slf4j
public class OrgController<_> {
    @Autowired
    private OrgService orgService;
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/getAllOrg")
    public Result getAllOrg(){
        List<Org> list = orgService.list();
       return Result.success(Code.GET_OK,list,"查询成功！");
    }

    @GetMapping("/getOrgByName")
    public Result getOrgByName(@RequestParam("name") String name){

        LambdaQueryWrapper<Org> wrapper = new LambdaQueryWrapper();
        wrapper.like(name!=null,Org::getName,name);
        List<Org> list = orgService.list(wrapper);
        return Result.success(Code.GET_OK,list,"查询成功！");
    }

/**
 * @author 黄冠瑛
 * @date 2022/5/5
 * 加入组织
 *
 */
    @PostMapping("/joinOrg")
    public Result joinOrg(Integer id,String token){
        if(token==null){ throw new BusinessException(Code.GET_ERR,"Token不能为空");}
            Long tokenId = new JwtHelper().getTokenId(token);
            UserInfo res = userInfoService.getById(tokenId);
            if(res.getOid()!=null){
                throw new BusinessException(Code.SAVE_ERR,"你已经加入一个组织了！");
            }else {
                res.setOid(id);
                userInfoService.updateById(res);
                return Result.success(Code.SAVE_OK,"加入成功！");

            }
        }





    /**
     * @author 黄冠瑛
     * @date 2022/5/5
     * 退出组织
     */
    @PostMapping("/outOrg")
    public Result outOrg(String token){
        Long tokenId = new JwtHelper().getTokenId(token);

        UserInfo byId = userInfoService.getById(tokenId);
        if (byId.getOid()==null){
            return Result.error(Code.SAVE_ERR,"你没有加入组织，退个毛啊！");
        }else {
        byId.setOid(null);
        userInfoService.updateById(byId);

        return Result.success(Code.SAVE_OK,byId,"退出成功！");
        }
    }





}
