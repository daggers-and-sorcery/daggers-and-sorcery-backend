package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.service.AttributeToRacialModifierConverter;
import com.morethanheroic.swords.attribute.service.cache.SkillAttributeDefinitionCache;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.AttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.data.GeneralAttributeData;
import com.morethanheroic.swords.attribute.service.calc.type.SkillTypeCalculator;
import com.morethanheroic.swords.attribute.service.modifier.calculator.GlobalAttributeModifierCalculator;
import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.race.model.RaceDefinition;
import com.morethanheroic.swords.race.model.modifier.RacialModifier;
import com.morethanheroic.swords.race.model.modifier.entry.NumericRacialModifierEntry;
import com.morethanheroic.swords.race.service.RaceDefinitionCache;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.service.SkillFacade;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Used to calculate a {@link com.morethanheroic.swords.attribute.domain.type.AttributeType#GENERAL} attribute's all data related to the player.
 */
@Service
public class GeneralAttributeCalculator extends GenericAttributeCalculator<GeneralAttribute> {

    private static final int STARTING_SKILL_LEVEL = 1;

    @Autowired
    private SkillFacade skillFacade;

    @Autowired
    private SkillTypeCalculator skillTypeCalculator;

    @Autowired
    private SkillAttributeDefinitionCache skillAttributeDefinitionCache;

    @Autowired
    private AttributeToRacialModifierConverter attributeToRacialModifierConverter;

    @Autowired
    private RaceDefinitionCache raceDefinitionCache;

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Autowired
    private GlobalAttributeModifierCalculator globalAttributeModifierCalculator;

    @Override
    public GeneralAttributeData calculateAttributeValue(UserEntity user, GeneralAttribute attribute) {
        return GeneralAttributeData.generalAttributeDataBuilder()
                .attribute(attribute)
                .actual(globalAttributeCalculator.calculateActualValue(user, attribute))
                .maximum(globalAttributeCalculator.calculateMaximumValue(user, attribute))
                .modifierData(globalAttributeModifierCalculator.calculateModifierData(user, attribute))
                .pointsToNextLevel(calculatePointsToAttributeLevel(user, attribute))
                .build();
    }

    @Override
    public Class<GeneralAttribute> getSupportedType() {
        return GeneralAttribute.class;
    }

    @Override
    public AttributeCalculationResult calculateActualBeforePercentageMultiplication(final UserEntity userEntity, final Attribute attribute) {
        final AttributeCalculationResult result = super.calculateActualBeforePercentageMultiplication(userEntity, attribute);

        result.increaseValue(attribute.getInitialValue());
        result.increaseValue(calculatePointsBonusBySkills(userEntity, attribute));

        return result;
    }

    @Override
    protected int calculatePercentageModification(UserEntity userEntity, Attribute attribute) {
        return getRacialModifierValue(userEntity.getRace(), (GeneralAttribute) attribute);
    }

    public int getRacialModifierValue(Race race, GeneralAttribute attribute) {
        final RacialModifier racialModifier = attributeToRacialModifierConverter.convert(attribute);
        final RaceDefinition raceDefinition = raceDefinitionCache.getDefinition(race);
        final NumericRacialModifierEntry racialModifierEntry = (NumericRacialModifierEntry) raceDefinition.getRacialModifier(racialModifier);

        if (racialModifierEntry == null) {
            return 0;
        }

        return racialModifierEntry.getValue();
    }

    public int calculatePointsToAttributeLevel(UserEntity user, Attribute attribute) {
        final SkillEntity skillEntity = skillFacade.getSkills(user);

        //TODO: this can be further optimized.
        int result = 0;
        for (SkillAttribute skill : SkillAttribute.values()) {
            if (skillAttributeDefinitionCache.getDefinition(skill).getIncrementedAttribute() == attribute) {
                result += skillEntity.getLevel(skillTypeCalculator.getSkillTypeFromSkillAttribute(skill)) - STARTING_SKILL_LEVEL;
            }
        }

        return result % 10;
    }

    public int calculatePointsBonusBySkills(UserEntity user, Attribute attribute) {
        final SkillEntity skillEntity = skillFacade.getSkills(user);

        //TODO: this can be further optimized.
        int result = 0;
        for (SkillAttribute skill : SkillAttribute.values()) {
            if (skillAttributeDefinitionCache.getDefinition(skill).getIncrementedAttribute() == attribute) {
                result += skillEntity.getLevel(skillTypeCalculator.getSkillTypeFromSkillAttribute(skill)) - STARTING_SKILL_LEVEL;
            }
        }

        return result / 10;
    }
}
