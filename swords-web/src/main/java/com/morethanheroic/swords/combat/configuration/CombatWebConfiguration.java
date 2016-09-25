package com.morethanheroic.swords.combat.configuration;

import com.morethanheroic.swords.combat.filter.CombatCorrectionFilter;
import com.morethanheroic.swords.combat.repository.domain.CombatMapper;
import com.morethanheroic.swords.user.service.UserEntityFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CombatWebConfiguration {

    @Bean
    public FilterRegistrationBean combatCorrectionFilterRegistrationBean(final CombatMapper combatMapper, final UserEntityFactory userEntityFactory) {
        return new FilterRegistrationBean(combatCorrectionFilter(combatMapper, userEntityFactory));
    }

    @Bean
    protected CombatCorrectionFilter combatCorrectionFilter(final CombatMapper combatMapper, final UserEntityFactory userEntityFactory) {
        return new CombatCorrectionFilter(combatMapper, userEntityFactory);
    }
}
