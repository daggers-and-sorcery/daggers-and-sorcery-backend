package com.morethanheroic.swords.explore.configuration;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.explore.filter.ExplorationEventOverrideFilter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ExplorationViewConfiguration {

    private static final String[] EXPLORATION_EVENT_OVERRIDE_FILTER_URLS = new String[]{"/*"};

    @Bean
    @Profile({"development"})
    public FilterRegistrationBean explorationEventOverrideFilterRegistrationBean(final ExplorationEventOverrideFilter explorationEventOverrideFilter) {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        registrationBean.setFilter(explorationEventOverrideFilter);
        registrationBean.setUrlPatterns(Lists.newArrayList(EXPLORATION_EVENT_OVERRIDE_FILTER_URLS));

        return registrationBean;
    }

    @Bean
    @Profile({"development"})
    public ExplorationEventOverrideFilter explorationEventOverrideFilter() {
        return new ExplorationEventOverrideFilter();
    }
}
