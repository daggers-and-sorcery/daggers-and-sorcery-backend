package com.morethanheroic.swords;

import com.morethanheroic.swords.common.sql.InstantHandler;
import com.morethanheroic.swords.common.sql.LocalDateHandler;
import com.morethanheroic.swords.common.sql.LocalTimeHandler;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

@Configuration
@RequiredArgsConstructor
public class SqlConfig {

    private final DataSource dataSource;

    @Bean
    public DataSourceTransactionManager transactionManager() throws URISyntaxException {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();

        sessionFactory.setDataSource(dataSource);

        sessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);

        sessionFactory.getObject().getConfiguration().getTypeHandlerRegistry().register(LocalDate.class, new LocalDateHandler());
        sessionFactory.getObject().getConfiguration().getTypeHandlerRegistry().register(LocalTime.class, new LocalTimeHandler());
        sessionFactory.getObject().getConfiguration().getTypeHandlerRegistry().register(Instant.class, new InstantHandler());

        return sessionFactory.getObject();
    }
}
