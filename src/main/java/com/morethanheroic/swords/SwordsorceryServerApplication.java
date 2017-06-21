package com.morethanheroic.swords;

import com.morethanheroic.application.web.WebApplication;
import com.morethanheroic.session.configuration.EnableSessionManagement;
import com.morethanheroic.swords.regeneration.interceptor.RegenerationInterceptor;
import com.morethanheroic.swords.user.view.resolver.UserEntityHandlerMethodArgumentResolver;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Repository;
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
@EnableSessionManagement(
        sessionProtectedUrls = {
                "/user/logout",
                "/character/*",
                "/map/*",
                "/shop/*",
                "/equip/*",
                "/item/use/*",
                "/journal/*",
                "/combat/*",
                "/skill/*",
                "/spell/*",
                "/explore/*",
                "/inn/*",
                "/witchhunters-guild/*",
        }
)
@MapperScan(value = "com.morethanheroic.swords", annotationClass = Repository.class)
public class SwordsorceryServerApplication extends WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwordsorceryServerApplication.class, args);
    }

    //TODO: Move these beans a separate config files!
    @Bean
    public ShaPasswordEncoder shaPasswordEncoder() {
        return new ShaPasswordEncoder(256);
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