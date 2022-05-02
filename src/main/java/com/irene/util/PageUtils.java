package com.irene.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: irene
 * @BelongsPackage: com.irene.util
 * @Auther: i.gy@outlook.com
 * @Date: 2022/5/6 15:04
 * @Description:
 * @since JDK 1.8
 *
 *
 * 将list转为 page
 */
public class PageUtils {
    public static Page getPage(Integer currentpage, Integer pageSize, List list){
        Page page = new Page();
        int size = list.size();
        if(pageSize > size) {
            pageSize = size;
        }
        //最大页数
        int maxPage = size % pageSize == 0 ? 1 :size / pageSize +1;
        currentpage = currentpage > maxPage? maxPage:currentpage;
        int index = currentpage > 1 ? (currentpage -1 ) * pageSize : 0; //第一条下标
        List res = new ArrayList();
        for (int i = 0; i <pageSize &&index+i<=size ; i++) {
                res.add(list.get(index+i));

        }
        page.setRecords(res).setCurrent(currentpage).setSize(pageSize).setTotal(size);
        return  page;
    }
            }
