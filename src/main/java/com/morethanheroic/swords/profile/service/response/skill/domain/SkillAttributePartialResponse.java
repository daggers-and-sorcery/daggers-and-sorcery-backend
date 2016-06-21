package com.morethanheroic.swords.profile.service.response.skill.domain;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.profile.service.response.AttributeCalculationResultPartialResponse;
import com.morethanheroic.swords.profile.service.response.AttributeDefinitionPartialResponse;
import com.morethanheroic.swords.profile.service.response.AttributeModifierPartialResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.Collection;

@Getter
@Builder
public class SkillAttributePartialResponse extends PartialResponse {

    private final AttributeCalculationResultPartialResponse actual;
    private final AttributeCalculationResultPartialResponse maximum;
    private final AttributeDefinitionPartialResponse attribute;
    private final Collection<AttributeModifierPartialResponse> modifierDataArray;

    private final int actualXp;
    private final int nextLevelXp;
    private final int xpBetweenLevels;

    private final int baseValue;
    private final int bonusValue;
}
