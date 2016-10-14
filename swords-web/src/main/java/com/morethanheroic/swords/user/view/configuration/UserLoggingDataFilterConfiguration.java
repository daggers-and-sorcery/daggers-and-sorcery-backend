package com.morethanheroic.swords.user.view.configuration;

import com.morethanheroic.swords.explore.view.filter.ExplorationEventOverrideFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Collections;

@Configuration
public class UserLoggingDataFilterConfiguration {

    @Bean
    @Profile({"development"})
    public FilterRegistrationBean explorationEventOverrideFilterRegistrationBean(final ExplorationEventOverrideFilter explorationEventOverrideFilter) {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        registrationBean.setFilter(explorationEventOverrideFilter);
        registrationBean.setUrlPatterns(Collections.singletonList("/*"));

        return registrationBean;
    }
}
