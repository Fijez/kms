package com.rtkit.fifth.element.kms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    final ApplicationContext applicationContext;

    @Autowired
    public MvcConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Override

    public void addInterceptors(InterceptorRegistry registry) {
        ControllerInterceptor controllerInterceptor = applicationContext.getBean(ControllerInterceptor.class);
        registry.addInterceptor(controllerInterceptor);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}