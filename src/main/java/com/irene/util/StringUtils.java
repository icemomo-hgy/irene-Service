package com.irene.util;

import java.util.UUID;

/**
 * @BelongsProject: irene
 * @BelongsPackage: com.irene.util
 * @Auther: i.gy@outlook.com
 * @Date: 2022/5/3 20:22
 * @Description:
 * @since JDK 1.8
 *
 * 字符串工具类
 *      图片名工具类
 */
public class StringUtils {


    public static String getImgName(String name){
        int i = name.lastIndexOf(".");
        String replace = UUID.randomUUID().toString().replace("-", "");
        String suffix = name.substring(i).toLowerCase();
            return  replace+suffix;
    }

}
