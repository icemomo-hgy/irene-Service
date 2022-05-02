package com.irene.util;

import com.irene.Excetion.BusinessException;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import com.qiniu.storage.BucketManager;
import java.io.FileInputStream;

/**
 * @BelongsProject: irene
 * @BelongsPackage: com.irene.util
 * @Auther: i.gy@outlook.com
 * @Date: 2022/5/3 19:54
 * @Description:
 * @since JDK 1.8
 */
@Slf4j
@Component
public class QiniuUtils {

    @Autowired
    private UploadManager uploadManager;

    @Autowired
    private Auth auth;
    @Value("${qiniu.bucketName}")
    private String bucketName;
    @Value("${qiniu.path}")
    private String url;
    public String upload(FileInputStream file, String fileName) throws QiniuException {
        String token = auth.uploadToken(bucketName);
        Response res = uploadManager.put(file, fileName, token, null, null);
        if (!res.isOK()) {
            throw new RuntimeException("上传出错:" + res);
        }
        return url+"/"+fileName;
    }
    public String reupload(FileInputStream file, String fileName,String key) throws QiniuException {
        Configuration cfg = new Configuration(Region.region2());
        BucketManager bucketManager = new BucketManager(auth, cfg);
        log.info(key);
        try {
            bucketManager.delete(bucketName, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
        String token = auth.uploadToken(bucketName);
        Response res = uploadManager.put(file, fileName, token, null, null);
        if (!res.isOK()) {
            throw new RuntimeException("上传出错:" + res);
        }



        return url+"/"+fileName;
    }

    public boolean delete(String key){
        Configuration cfg = new Configuration(Region.region2());
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucketName,key);
            return true;
        } catch (QiniuException e) {
            return false;

        }


    }


}
