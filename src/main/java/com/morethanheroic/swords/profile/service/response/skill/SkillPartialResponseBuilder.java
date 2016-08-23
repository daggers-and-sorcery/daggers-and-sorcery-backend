package com.morethanheroic.swords.profile.service.response.skill;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.profile.service.response.skill.domain.SkillAttributePartialResponseBuilderConfiguration;
import com.morethanheroic.swords.profile.service.response.skill.domain.SkillPartialResponse;
import com.morethanheroic.swords.profile.service.response.skill.domain.SkillPartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SkillPartialResponseBuilder implements PartialResponseBuilder<SkillPartialResponseBuilderConfiguration> {

    private final SkillAttributePartialResponseCollectionBuilder skillAttributePartialResponseCollectionBuilder;

    @Override
    public PartialResponse build(SkillPartialResponseBuilderConfiguration skillPartialResponseBuilderConfiguration) {
        return SkillPartialResponse.builder()
                .tradeSkills(skillAttributePartialResponseCollectionBuilder.build(
                        SkillAttributePartialResponseBuilderConfiguration.builder()
                                .userEntity(skillPartialResponseBuilderConfiguration.getUserEntity())
                                .skills(skillPartialResponseBuilderConfiguration.getTradeSkills())
                                .build()
                        )
                ).combatSkills(skillAttributePartialResponseCollectionBuilder.build(
                        SkillAttributePartialResponseBuilderConfiguration.builder()
                                .userEntity(skillPartialResponseBuilderConfiguration.getUserEntity())
                                .skills(skillPartialResponseBuilderConfiguration.getCombatSkills())
                                .build()
                        )
                ).magicSkills(skillAttributePartialResponseCollectionBuilder.build(
                        SkillAttributePartialResponseBuilderConfiguration.builder()
                                .userEntity(skillPartialResponseBuilderConfiguration.getUserEntity())
                                .skills(skillPartialResponseBuilderConfiguration.getMagicSkills())
                                .build()
                        )
                ).shadowSkills(skillAttributePartialResponseCollectionBuilder.build(
                        SkillAttributePartialResponseBuilderConfiguration.builder()
                                .userEntity(skillPartialResponseBuilderConfiguration.getUserEntity())
                                .skills(skillPartialResponseBuilderConfiguration.getShadowSkills())
                                .build()
                        )
                )
                .build();
    }
}
