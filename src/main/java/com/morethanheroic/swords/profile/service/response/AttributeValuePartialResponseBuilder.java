package com.morethanheroic.swords.profile.service.response;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierType;
import com.morethanheroic.swords.attribute.domain.type.AttributeType;
import com.morethanheroic.swords.attribute.service.AttributeUtil;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.CombatAttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.data.AttributeData;
import com.morethanheroic.swords.attribute.service.calc.domain.data.GeneralAttributeData;
import com.morethanheroic.swords.attribute.service.calc.domain.data.SkillAttributeData;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//Todo: use own Configuration, not the ProfileInfoBuilder's one
public class AttributeValuePartialResponseBuilder implements PartialResponseCollectionBuilder<ProfileInfoResponseBuilderConfiguration> {

    @NonNull
    private final AttributeDefinitionPartialResultBuilder attributeDefinitionPartialResultBuilder;

    @NonNull
    private final AttributeModifierPartialResponseBuilder attributeModifierPartialResponseBuilder;

    @NonNull
    private final GlobalAttributeCalculator globalAttributeCalculator;

    @NonNull
    private final AttributeUtil attributeUtil;

    @NonNull
    private final AttributeCalculationResultPartialResponseBuilder attributeCalculationResultPartialResponseBuilder;

    @NonNull
    private final CombatAttributeCalculationResultPartialResponseBuilder combatAttributeCalculationResultPartialResponseBuilder;

    private Set<Attribute> attributes;

    @PostConstruct
    private void initialize() {
        attributes = attributeUtil.getAllAttributes();
    }

    @Override
    public Collection<PartialResponse> build(ProfileInfoResponseBuilderConfiguration responseBuilderConfiguration) {
        //Do NOT convert this to streams! That will modify the ordering!
        final LinkedList<PartialResponse> result = new LinkedList<>();
        for (Attribute attribute : attributes) {
            result.add(buildAttributeResponse(responseBuilderConfiguration.getUserEntity(), attribute));
        }

        return result;
    }

    private PartialResponse buildAttributeResponse(UserEntity user, Attribute attribute) {
        final AttributeData attributeData = globalAttributeCalculator.calculateAttributeValue(user, attribute);

        final AttributeValuePartialResponse.AttributeValuePartialResponseBuilder attributeValuePartialResponseBuilder = AttributeValuePartialResponse.builder()
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
                ));

        if (attribute.getAttributeType() == AttributeType.COMBAT) {
            attributeValuePartialResponseBuilder
                    .actual(combatAttributeCalculationResultPartialResponseBuilder.build(
                            CombatAttributeCalculationResultPartialResponseConfiguration.builder()
                                    .combatAttributeCalculationResult((CombatAttributeCalculationResult) attributeData.getActual())
                                    .build()
                    ))
                    .maximum(combatAttributeCalculationResultPartialResponseBuilder.build(
                            CombatAttributeCalculationResultPartialResponseConfiguration.builder()
                                    .combatAttributeCalculationResult((CombatAttributeCalculationResult) attributeData.getActual())
                                    .build()
                    ));
        } else {
            attributeValuePartialResponseBuilder
                    .actual(attributeCalculationResultPartialResponseBuilder.build(
                            AttributeCalculationResultPartialResponseConfiguration.builder()
                                    .attributeCalculationResult(attributeData.getActual())
                                    .build()
                    ))
                    .maximum(attributeCalculationResultPartialResponseBuilder.build(
                            AttributeCalculationResultPartialResponseConfiguration.builder()
                                    .attributeCalculationResult(attributeData.getActual())
                                    .build()
                    ));
        }

        if (attribute.getAttributeType() == AttributeType.SKILL) {
            final SkillAttributeData skillAttributeData = (SkillAttributeData) attributeData;

            attributeValuePartialResponseBuilder
                    .actualXp(skillAttributeData.getActualXp())
                    .nextLevelXp(skillAttributeData.getNextLevelXp())
                    .xpBetweenLevels(skillAttributeData.getXpBetweenLevels());

            int baseValue = 0;
            int bonusValue = 0;
            for (AttributeModifierEntry attributeModifierEntry : skillAttributeData.getModifierData()) {
                if (attributeModifierEntry.getAttributeModifierType() == AttributeModifierType.LEVEL) {
                    baseValue += attributeModifierEntry.getAttributeModifierValue().getValue();
                } else {
                    bonusValue += attributeModifierEntry.getAttributeModifierValue().getValue();
                }
            }

            attributeValuePartialResponseBuilder
                    .baseValue(baseValue)
                    .bonusValue(bonusValue);
        }

        if (attribute.getAttributeType() == AttributeType.GENERAL) {
            attributeValuePartialResponseBuilder.pointsToNextLevel(((GeneralAttributeData) attributeData).getPointsToNextLevel());
        }

        return attributeValuePartialResponseBuilder.build();
    }
}
