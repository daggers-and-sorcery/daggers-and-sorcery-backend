package com.morethanheroic.swords.profile.response.service.attribute;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.domain.SpecialAttribute;
import com.morethanheroic.swords.attribute.domain.type.AttributeType;
import com.morethanheroic.swords.attribute.service.AttributeUtil;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.DiceValueAttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.data.AttributeData;
import com.morethanheroic.swords.attribute.service.calc.domain.data.GeneralAttributeData;
import com.morethanheroic.swords.profile.response.service.ProfileInfoResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
//Todo: use own Configuration, not the ProfileInfoBuilder's one
public class AttributeValuePartialResponseBuilder implements PartialResponseCollectionBuilder<ProfileInfoResponseBuilderConfiguration> {

    private final AttributeDefinitionPartialResultBuilder attributeDefinitionPartialResultBuilder;
    private final AttributeModifierPartialResponseBuilder attributeModifierPartialResponseBuilder;
    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final AttributeUtil attributeUtil;
    private final AttributeCalculationResultPartialResponseBuilder attributeCalculationResultPartialResponseBuilder;
    private final CombatAttributeCalculationResultPartialResponseBuilder combatAttributeCalculationResultPartialResponseBuilder;

    @Override
    public Collection<PartialResponse> build(ProfileInfoResponseBuilderConfiguration responseBuilderConfiguration) {
        return attributeUtil.getAllAttributes().stream()
                .filter(this::shouldSendBasedOnAttribute)
                .map(attribute -> globalAttributeCalculator.calculateAttributeValue(responseBuilderConfiguration.getUserEntity(), attribute))
                .filter(this::shouldSendBasedOnAttributeData)
                .map(this::buildAttributeResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    private PartialResponse buildAttributeResponse(final AttributeData attributeData) {
        final AttributeValuePartialResponse.AttributeValuePartialResponseBuilder attributeValuePartialResponseBuilder = AttributeValuePartialResponse.builder()
                .attribute(attributeDefinitionPartialResultBuilder.build(
                        AttributeDefinitionPartialResponseBuilderConfiguration.builder()
                                .attribute(attributeData.getAttribute())
                                .build()
                ))
                .modifierDataArray(attributeModifierPartialResponseBuilder.build(
                        AttributeModifierPartialResponseBuilderConfiguration.builder()
                                .attribute(attributeData.getAttribute())
                                .modifierData(attributeData.getModifierData())
                                .build()
                ));

        if (attributeData.getAttribute().getAttributeType() == AttributeType.COMBAT) {
            attributeValuePartialResponseBuilder
                    .actual(combatAttributeCalculationResultPartialResponseBuilder.build(
                            CombatAttributeCalculationResultPartialResponseConfiguration.builder()
                                    .combatAttributeCalculationResult((DiceValueAttributeCalculationResult) attributeData.getActual())
                                    .build()
                    ))
                    .maximum(combatAttributeCalculationResultPartialResponseBuilder.build(
                            CombatAttributeCalculationResultPartialResponseConfiguration.builder()
                                    .combatAttributeCalculationResult((DiceValueAttributeCalculationResult) attributeData.getActual())
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

        if (attributeData.getAttribute().getAttributeType() == AttributeType.GENERAL) {
            attributeValuePartialResponseBuilder.pointsToNextLevel(((GeneralAttributeData) attributeData).getPointsToNextLevel());
        }

        return attributeValuePartialResponseBuilder.build();
    }

    private boolean shouldSendBasedOnAttribute(final Attribute attribute) {
        return !(attribute instanceof SkillAttribute);
    }

    private boolean shouldSendBasedOnAttributeData(final AttributeData attributeData) {
        return !(attributeData.getAttribute() instanceof SpecialAttribute) || (attributeData.getAttribute() instanceof SpecialAttribute && !attributeData.getActual().isEmpty());
    }
}
