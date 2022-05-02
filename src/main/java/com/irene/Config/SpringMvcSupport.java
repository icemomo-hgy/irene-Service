package com.irene.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @BelongsProject: irene
 * @BelongsPackage: com.irene.Config
 * @Auther: i.gy@outlook.com
 * @Date: 2022/5/5 15:38
 * @Description:
 * @since JDK 1.8
 */
@Configuration
@ComponentScan({"com.irene.Controller"})
public class SpringMvcSupport extends WebMvcConfigurationSupport {
    @Autowired
    private ProjectInterceptor projectInterceptor;

    protected void  addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(projectInterceptor).addPathPatterns("/joinOrg");
        registry.addInterceptor(projectInterceptor).addPathPatterns("/outOrg");
        registry.addInterceptor(projectInterceptor).addPathPatterns("/joinService");
        registry.addInterceptor(projectInterceptor).addPathPatterns("/getServiceHistory");
        registry.addInterceptor(projectInterceptor).addPathPatterns("/sendCommunity");
        registry.addInterceptor(projectInterceptor).addPathPatterns("/likePost");
        registry.addInterceptor(projectInterceptor).addPathPatterns("/comment");
        registry.addInterceptor(projectInterceptor).addPathPatterns("/deleteCommunity");
        registry.addInterceptor(projectInterceptor).addPathPatterns("/myCommunity");




    }
}
