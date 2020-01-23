package com.example.thymeleaf.config;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("classpath:templates/");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/static/**")
            .addResourceLocations("classpath:static/");
    }

    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.addDialect(new LayoutDialect());
        return engine;
    }
}
