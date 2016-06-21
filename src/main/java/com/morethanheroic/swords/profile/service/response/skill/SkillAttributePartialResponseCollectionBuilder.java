package com.morethanheroic.swords.profile.service.response.skill;

import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierType;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.service.calc.domain.data.SkillAttributeData;
import com.morethanheroic.swords.attribute.service.calc.type.SkillTypeCalculator;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;
import com.morethanheroic.swords.profile.service.response.*;
import com.morethanheroic.swords.profile.service.response.skill.domain.SkillAttributePartialResponse;
import com.morethanheroic.swords.profile.service.response.skill.domain.SkillAttributePartialResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.domain.SkillType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class SkillAttributePartialResponseCollectionBuilder implements PartialResponseCollectionBuilder<SkillAttributePartialResponseBuilderConfiguration> {

    @Autowired
    private AttributeDefinitionPartialResultBuilder attributeDefinitionPartialResultBuilder;

    @Autowired
    private AttributeModifierPartialResponseBuilder attributeModifierPartialResponseBuilder;

    @Autowired
    private AttributeCalculationResultPartialResponseBuilder attributeCalculationResultPartialResponseBuilder;

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Autowired
    private SkillTypeCalculator skillTypeCalculator;

    @Override
    public List<SkillAttributePartialResponse> build(final SkillAttributePartialResponseBuilderConfiguration skillAttributePartialResponseBuilderConfiguration) {
        final List<SkillAttributePartialResponse> skillAttributePartialResponse = new LinkedList<>();
        for (SkillType skillType : skillAttributePartialResponseBuilderConfiguration.getSkills()) {
            final SkillAttribute attribute = skillTypeCalculator.getSkillAttributeFromSkillType(skillType);

            //TODO: this calculation should happen somewhere else!!!
            final SkillAttributeData attributeData = (SkillAttributeData) globalAttributeCalculator.calculateAttributeValue(skillAttributePartialResponseBuilderConfiguration.getUserEntity(), attribute);

            int baseValue = 0;
            int bonusValue = 0;
            for (AttributeModifierEntry attributeModifierEntry : attributeData.getModifierData()) {
                if (attributeModifierEntry.getAttributeModifierType() == AttributeModifierType.LEVEL) {
                    baseValue += attributeModifierEntry.getAttributeModifierValue().getValue();
                } else {
                    bonusValue += attributeModifierEntry.getAttributeModifierValue().getValue();
                }
            }

            skillAttributePartialResponse.add(
                    SkillAttributePartialResponse.builder()
                            .attribute(attributeDefinitionPartialResultBuilder.build(
                                    AttributeDefinitionPartialResponseBuilderConfiguration.builder()
                                            .attribute(attribute)
                                            .build()
                            ))
                            .modifierDataArray(attributeModifierPartialResponseBuilder.build(
                                    AttributeModifierPartialResponseBuilderConfiguration.builder()
                                            .attribute(attribute)
                                            .modifierData(attributeData.getModifierData())
                                            .build()
                            ))
                            .actual(attributeCalculationResultPartialResponseBuilder.build(
                                    AttributeCalculationResultPartialResponseConfiguration.builder()
                                            .attributeCalculationResult(attributeData.getActual())
                                            .build()
                            ))
                            .maximum(attributeCalculationResultPartialResponseBuilder.build(
                                    AttributeCalculationResultPartialResponseConfiguration.builder()
                                            .attributeCalculationResult(attributeData.getActual())
                                            .build()
                            ))
                            .actualXp(attributeData.getActualXp())
                            .nextLevelXp(attributeData.getNextLevelXp())
                            .xpBetweenLevels(attributeData.getXpBetweenLevels())
                            .baseValue(baseValue)
                            .bonusValue(bonusValue)
                            .build()
            );
        }
        return Collections.unmodifiableList(skillAttributePartialResponse);
    }
}
