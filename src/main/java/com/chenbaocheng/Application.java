package com.chenbaocheng;

/**
 * Created by CBC on 2015-04-08 18:20.
 */

import com.chenbaocheng.interceptors.AdminInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.setShowBanner(true);
        application.run(args);
    }

    /**
     * 配置拦截器
     *
     * @param registry
     */
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/manager/**").excludePathPatterns("/manager/login").excludePathPatterns("/manager/loginAjax");
    }

    /**
     * spring boot 定时任务
     * <p/>
     */
    @Scheduled(cron = "0 0 22 * * ?")
    public void scheduled() {

    }
}