package com.morethanheroic.swords.profile.response.service.attribute;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.domain.type.AttributeType;
import com.morethanheroic.swords.attribute.service.AttributeUtil;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.DiceValueAttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.data.AttributeData;
import com.morethanheroic.swords.attribute.service.calc.domain.data.GeneralAttributeData;
import com.morethanheroic.swords.profile.response.service.ProfileInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

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
        return attributes.stream()
            //Skipping skill attributes because they are already refactored from this mess.
            .filter(attribute -> !(attribute instanceof SkillAttribute))
            .map(attribute -> buildAttributeResponse(responseBuilderConfiguration.getUserEntity(), attribute))
            .collect(Collectors.toCollection(LinkedList::new));
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

        if (attribute.getAttributeType() == AttributeType.GENERAL) {
            attributeValuePartialResponseBuilder.pointsToNextLevel(((GeneralAttributeData) attributeData).getPointsToNextLevel());
        }

        return attributeValuePartialResponseBuilder.build();
    }
}
