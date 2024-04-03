package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // 모든 origin 허용, 필요에 따라 변경 가능
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메서드 지정
                .allowedHeaders("*"); // 모든 헤더 허용, 필요에 따라 변경 가능
    }
}
