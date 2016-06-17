package com.morethanheroic.swords.skill.view.response.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.skill.view.response.domain.configuration.SkillLevelPartialResponse;
import com.morethanheroic.swords.skill.view.response.domain.configuration.SkillLevelPartialResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class SkillLevelPartialResponseBuilder implements PartialResponseBuilder<SkillLevelPartialResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(SkillLevelPartialResponseBuilderConfiguration skillLevelPartialResponseBuilderConfiguration) {
        return SkillLevelPartialResponse.builder()
                .level(skillLevelPartialResponseBuilderConfiguration.getSkillLevel())
                .build();
    }
}
