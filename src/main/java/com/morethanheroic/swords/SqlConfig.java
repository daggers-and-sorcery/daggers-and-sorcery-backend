package com.morethanheroic.swords;

import com.morethanheroic.swords.common.sql.InstantHandler;
import com.morethanheroic.swords.common.sql.LocalDateHandler;
import com.morethanheroic.swords.common.sql.LocalTimeHandler;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

@Configuration
public class SqlConfig {

    @Value("${database.ip}")
    private String databaseIp;

    @Value("${database.user}")
    private String user;

    @Value("${database.password}")
    private String password;

    @Bean
    public DataSource getMybatisDataSource() throws URISyntaxException {
        final BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");

        dataSource.setUrl("jdbc:mysql://" + databaseIp + ":3306/swords");
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() throws URISyntaxException {
        return new DataSourceTransactionManager(getMybatisDataSource());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();

        sessionFactory.setDataSource(getMybatisDataSource());

        sessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);

        sessionFactory.getObject().getConfiguration().getTypeHandlerRegistry().register(LocalDate.class, new LocalDateHandler());
        sessionFactory.getObject().getConfiguration().getTypeHandlerRegistry().register(LocalTime.class, new LocalTimeHandler());
        sessionFactory.getObject().getConfiguration().getTypeHandlerRegistry().register(Instant.class, new InstantHandler());

        return sessionFactory.getObject();
    }
}
