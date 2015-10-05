package com.morethanheroic.swords;

import com.morethanheroic.swords.common.interceptor.RegenerationInterceptor;
import com.morethanheroic.swords.common.resolver.UserEntityHandlerMethodArgumentResolver;
import com.morethanheroic.swords.common.session.filter.SessionLoginFilter;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
@EnableAutoConfiguration
@ComponentScan(lazyInit = true)
@EnableWebMvc
@EnableTransactionManagement
@EnableRedisHttpSession
@MapperScan("com.morethanheroic.swords")
public class SwordsorceryServerApplication extends WebMvcAutoConfigurationAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SwordsorceryServerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SwordsorceryServerApplication.class, args);
    }

    @Bean
    public ShaPasswordEncoder shaPasswordEncoder() {
        return new ShaPasswordEncoder(256);
    }

    @Bean
    public FilterRegistrationBean filterSessionLoginBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        SessionLoginFilter sessionFilter = new SessionLoginFilter();

        registrationBean.setFilter(sessionFilter);

        ArrayList<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/user/logout");
        urlPatterns.add("/character/*");
        urlPatterns.add("/map/*");

        registrationBean.setUrlPatterns(urlPatterns);

        return registrationBean;
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
    public DataSource getMybatisDataSource() throws URISyntaxException {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");

        //Production
        if (System.getenv("CLEARDB_DATABASE_URL") != null) {
            URI dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));

            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();

            dataSource.setUrl(dbUrl);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
        } else {
            dataSource.setUrl("jdbc:mysql://localhost:3306/swords");
            dataSource.setUsername("root");
            dataSource.setPassword("");
        }

        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() throws URISyntaxException {
        return new DataSourceTransactionManager(getMybatisDataSource());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(getMybatisDataSource());
        sessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);

        return sessionFactory.getObject();
    }

    @Bean
    public Random getRandom() {
        return new Random();
    }

    @Bean
    public JedisConnectionFactory redisConnectionFactory() throws URISyntaxException {
        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();

        if (System.getenv("REDISCLOUD_URL") != null) {
            URI redisURI = new URI(System.getenv("REDISCLOUD_URL"));

            redisConnectionFactory.setHostName(redisURI.getHost());
            redisConnectionFactory.setPort(redisURI.getPort());
            redisConnectionFactory.setPassword(redisURI.getUserInfo().split(":", 2)[1]);
        } else {
            redisConnectionFactory.setHostName("127.0.0.1");
            redisConnectionFactory.setPort(6379);
        }

        return redisConnectionFactory;
    }
}