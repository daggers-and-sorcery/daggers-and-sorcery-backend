package com.morethanheroic.swords;

import com.morethanheroic.application.configuration.CorsProperties;
import com.morethanheroic.application.configuration.ResourcesProperties;
import com.morethanheroic.application.web.WebApplication;
import com.morethanheroic.dependencyinjection.resolver.HandlerMethodArgumentResolverRegistrationBean;
import com.morethanheroic.swords.regeneration.interceptor.RegenerationInterceptor;
import com.morethanheroic.swords.user.view.resolver.UserEntityHandlerMethodArgumentResolver;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import java.util.List;
import java.util.Random;

@Configuration
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
@ComponentScan(
        basePackages = {
                "com.morethanheroic",
        },
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.morethanheroic.login.*"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.morethanheroic.user.*")
        }
)
@EnableWebMvc
@EnableScheduling
@EnableTransactionManagement
@MapperScan(value = "com.morethanheroic", annotationClass = Mapper.class)
public class SwordsorceryServerApplication extends WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwordsorceryServerApplication.class, args);
    }

    public SwordsorceryServerApplication(
            final List<HandlerMethodArgumentResolverRegistrationBean> handlerMethodArgumentResolverRegistrationBeans,
            final CorsProperties corsProperties, final ResourcesProperties resourcesProperties) {
        super(handlerMethodArgumentResolverRegistrationBeans, corsProperties, resourcesProperties);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getRegenerationInterceptor());
    }

    @Bean
    public RegenerationInterceptor getRegenerationInterceptor() {
        return new RegenerationInterceptor();
    }

    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);

        argumentResolvers.add(getUserEntityHandlerMethodArgumentResolver());
    }

    @Bean
    public UserEntityHandlerMethodArgumentResolver getUserEntityHandlerMethodArgumentResolver() {
        return new UserEntityHandlerMethodArgumentResolver();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/static/");
    }

    @Bean
    public Random getRandom() {
        return new Random();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
}