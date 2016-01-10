package com.morethanheroic.swords.profile.service.response;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.type.AttributeType;
import com.morethanheroic.swords.attribute.service.AttributeUtil;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.service.calc.domain.data.AttributeData;
import com.morethanheroic.swords.attribute.service.calc.domain.data.GeneralAttributeData;
import com.morethanheroic.swords.attribute.service.calc.domain.data.SkillAttributeData;
import com.morethanheroic.swords.response.domain.PartialResponse;
import com.morethanheroic.swords.response.service.PartialResponseCollectionBuilder;
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

        return AttributeValuePartialResponse.builder()
                .actual(attributeData.getActual())
                .maximum(attributeData.getMaximum())
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
                .pointsToNextLevel(buildPointsToNextLevel(attributeData))
                .actualXp(buildActualXp(attributeData))
                .nextLevelXp(buildNextLevelXp(attributeData))
                .xpBetweenLevels(buildXpBetweenLevels(attributeData))
                .build();
    }

    //ToDO: Only call and add this if the attribute is GENERAL
    private Integer buildPointsToNextLevel(AttributeData attributeData) {
        if (attributeData.getAttribute().getAttributeType() == AttributeType.GENERAL) {
            return ((GeneralAttributeData) attributeData).getPointsToNextLevel();
        }

        return null;
    }

    //ToDO: Only call and add this if the attribute is SKILL
    private Integer buildActualXp(AttributeData attributeData) {
        if (attributeData.getAttribute().getAttributeType() == AttributeType.SKILL) {
            return ((SkillAttributeData) attributeData).getActualXp();
        }

        return null;
    }

    //ToDO: Only call and add this if the attribute is SKILL
    private Integer buildNextLevelXp(AttributeData attributeData) {
        if (attributeData.getAttribute().getAttributeType() == AttributeType.SKILL) {
            return ((SkillAttributeData) attributeData).getNextLevelXp();
        }

        return null;
    }

    //ToDO: Only call and add this if the attribute is SKILL
    private Integer buildXpBetweenLevels(AttributeData attributeData) {
        if (attributeData.getAttribute().getAttributeType() == AttributeType.SKILL) {
            return ((SkillAttributeData) attributeData).getXpBetweenLevels();
        }

        return null;
    }
}
