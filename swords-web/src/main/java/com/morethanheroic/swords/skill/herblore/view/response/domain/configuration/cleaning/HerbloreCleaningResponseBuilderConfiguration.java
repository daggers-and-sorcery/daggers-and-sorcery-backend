package com.morethanheroic.swords.skill.herblore.view.response.domain.configuration.cleaning;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.herblore.service.cleaning.domain.CleaningResult;
import com.morethanheroic.swords.skill.herblore.view.response.service.cleaning.HerbloreCleaningResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

/**
 * A configuration for {@link HerbloreCleaningResponseBuilder}.
 */
@Builder
@Getter
public class HerbloreCleaningResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final CleaningResult cleaningResult;
}
