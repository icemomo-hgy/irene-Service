package com.irene.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.irene.Entity.Goods;
import com.irene.Entity.Sort;
import com.irene.Service.GoodsService;
import com.irene.Service.SortService;
import com.irene.common.Code;
import com.irene.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @BelongsProject: irene
 * @BelongsPackage: com.irene.Controller
 * @Auther: i.gy@outlook.com
 * @Date: 2022/5/8 13:56
 * @Description:
 * @since JDK 1.8
 */
@RestController
@Slf4j
public class ShoppingController {
    @Autowired
    private SortService sortService;
    @Autowired
    private GoodsService goodsService;
    @GetMapping("/getSort")
    public Result getSort(){
        List<Sort> list = sortService.list();
    return Result.success(Code.GET_OK,list,"成功！");
    }

    @GetMapping("/getGoods")
    public Result getGoods(@RequestParam(value = "id",required = false,defaultValue = "1") Integer id){
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Goods::getSid,id);
        List<Goods> list = goodsService.list(wrapper);
        return Result.success(Code.GET_OK,list,"查询成功");

    }
}
