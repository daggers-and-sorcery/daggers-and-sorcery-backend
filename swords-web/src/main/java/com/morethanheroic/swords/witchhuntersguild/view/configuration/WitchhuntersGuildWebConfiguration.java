package com.morethanheroic.swords.witchhuntersguild.view.configuration;

import com.morethanheroic.swords.witchhuntersguild.view.filter.WitchhuntersGuildAccessibilityFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WitchhuntersGuildWebConfiguration {

    private static final String[] FILTERED_PAGES = new String[]{
            "/witchhunters-guild/quest/hand-in",
            "/witchhunters-guild/quest",
            "/witchhunters-guild/shop",
            "/witchhunters-guild/guild-hall"
    };

    @Bean
    public FilterRegistrationBean witchhuntersGuildFilterRegistrationBean(final WitchhuntersGuildAccessibilityFilter witchhuntersGuildAccessibilityFilter) {
        final FilterRegistrationBean witchhuntersGuildAccessibilityFilterRegistrationBean = new FilterRegistrationBean(witchhuntersGuildAccessibilityFilter);

        witchhuntersGuildAccessibilityFilterRegistrationBean.addUrlPatterns(FILTERED_PAGES);

        return witchhuntersGuildAccessibilityFilterRegistrationBean;
    }
}
