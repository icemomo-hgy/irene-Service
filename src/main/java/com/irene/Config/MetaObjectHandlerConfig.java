package com.irene.Config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @BelongsProject: irene
 * @BelongsPackage: com.irene.Config
 * @Auther: i.gy@outlook.com
 * @Date: 2022/5/5 16:29
 * @Description:
 * @since JDK 1.8
 */
@Component
public class MetaObjectHandlerConfig implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDate.class, LocalDate.now()); // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "registerTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject,"publishTime",LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject,"date",LocalDate.class,LocalDate.now());

    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
