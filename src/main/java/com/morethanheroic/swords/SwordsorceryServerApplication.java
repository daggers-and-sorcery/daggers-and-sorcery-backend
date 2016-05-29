package com.morethanheroic.swords;

import com.morethanheroic.application.Application;
import com.morethanheroic.session.configuration.EnableSessionManagement;
import com.morethanheroic.swords.common.interceptor.RegenerationInterceptor;
import com.morethanheroic.swords.common.resolver.UserEntityHandlerMethodArgumentResolver;
import com.morethanheroic.swords.common.sql.InstantHandler;
import com.morethanheroic.swords.common.sql.LocalDateHandler;
import com.morethanheroic.swords.common.sql.LocalTimeHandler;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

@Configuration
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
@ComponentScan({
        "com.morethanheroic.swords",
        "com.morethanheroic.math"
})
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
                "/inn/*"
        }
)
@MapperScan(value = "com.morethanheroic.swords", annotationClass = Repository.class)
public class SwordsorceryServerApplication extends Application {

    public static void main(String[] args) {
        SpringApplication.run(SwordsorceryServerApplication.class, args);
    }

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
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
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