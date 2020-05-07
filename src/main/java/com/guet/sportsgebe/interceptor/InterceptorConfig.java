package com.guet.sportsgebe.interceptor;

import com.guet.sportsgebe.interceptor.handleInterceptor.AllHandleInterceptor;
import com.guet.sportsgebe.interceptor.handleInterceptor.LoginHandleInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig  extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry ){
        registry.addInterceptor(new LoginHandleInterceptor()).addPathPatterns("/Users/CheckLogin");
        registry.addInterceptor(new AllHandleInterceptor())
                .addPathPatterns("/Activity/GoCreateAct")
                .addPathPatterns("/Users/GoOtherPerson")
                .addPathPatterns("/Article/GoCreateArticle")
                .addPathPatterns("/Article/GetOwnArticle")
                .addPathPatterns("/Article/GoOtherArticle")
                .addPathPatterns("/Users/GoPerson")
                .addPathPatterns("Users/GoOtherPerson")
                .addPathPatterns("Activity/GoChangeAct");
        //网站配置生成器：添加一个拦截器，拦截路径为整个项目
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/images/**").addResourceLocations("file:/C:/Users/lzp/Desktop/images/");
        registry.addResourceHandler("/txt/**").addResourceLocations("file:/C:/Users/lzp/Desktop/txt/");
        super.addResourceHandlers(registry);
    }
}
