package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.BasicAttribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.service.AttributeToRacialModifierConverter;
import com.morethanheroic.swords.attribute.service.bonus.AttributeBonusProvider;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.AttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.CombatAttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.UnlimitedAttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.data.AttributeData;
import com.morethanheroic.swords.attribute.service.calc.type.SkillTypeCalculator;
import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.race.model.RaceDefinition;
import com.morethanheroic.swords.race.model.modifier.RacialModifier;
import com.morethanheroic.swords.race.model.modifier.entry.NumericRacialModifierEntry;
import com.morethanheroic.swords.race.service.RaceDefinitionCache;
import com.morethanheroic.swords.skill.service.SkillFacade;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This calculator is used when you don't know the attribute's type or it doesn't matter for you. It will automatically
 * delegate the calls to the appropriate {@link AttributeCalculator}.
 */
@Service
public class GlobalAttributeCalculator {

    @Autowired
    private GeneralAttributeCalculator generalAttributeCalculator;

    @Autowired
    private CombatAttributeCalculator combatAttributeCalculator;

    @Autowired
    private SkillFacade skillFacade;

    @Autowired
    private AttributeToRacialModifierConverter attributeToRacialModifierConverter;

    @Autowired
    private RaceDefinitionCache raceDefinitionCache;

    @Autowired
    private SkillTypeCalculator skillTypeCalculator;

    @Autowired
    private List<AttributeBonusProvider> attributeBonusProviders;

    @Autowired
    private AttributeCalculatorLocator attributeCalculatorLocator;

    @SuppressWarnings("unchecked")
    public AttributeData calculateAttributeValue(UserEntity user, Attribute attribute) {
        return attributeCalculatorLocator.getCalculator(attribute).calculateAttributeValue(user, attribute);
    }

    public AttributeCalculationResult calculateActualBeforePercentageMultiplication(UserEntity user, Attribute attribute) {
        final AttributeCalculationResult result;

        if (attribute instanceof CombatAttribute) {
            result = new CombatAttributeCalculationResult((CombatAttribute) attribute);
        } else {
            result = new AttributeCalculationResult(attribute);
        }

        if (attribute instanceof SkillAttribute) {
            result.increaseValue(skillFacade.getSkills(user).getLevel(skillTypeCalculator.getSkillTypeFromSkillAttribute((SkillAttribute) attribute)));
        } else if (attribute instanceof GeneralAttribute) {
            result.increaseValue(attribute.getInitialValue());
            result.increaseValue(generalAttributeCalculator.calculatePointsBonusBySkills(user, attribute));
        } else if (attribute instanceof CombatAttribute) {
            result.increaseValue(attribute.getInitialValue());
            result.increaseValue(combatAttributeCalculator.calculateAllBonusByGeneralAttributes(user, (CombatAttribute) attribute));
        } else {
            result.increaseValue(attribute.getInitialValue());
        }

        for (AttributeBonusProvider attributeBonusProvider : attributeBonusProviders) {
            result.addCalculationResult(attributeBonusProvider.calculateBonus(user, attribute));
        }

        return result;
    }

    public AttributeCalculationResult calculateActualValue(UserEntity user, Attribute attribute) {
        return calculateActualValue(user, attribute, true);
    }

    public AttributeCalculationResult calculateActualValue(UserEntity user, Attribute attribute, boolean shouldCheckMinimum) {
        if (attribute == CombatAttribute.LIFE) {
            return new CombatAttributeCalculationResult(user.getHealthPoints(), (CombatAttribute) attribute);
        } else if (attribute == CombatAttribute.MANA) {
            return new CombatAttributeCalculationResult(user.getManaPoints(), (CombatAttribute) attribute);
        } else if (attribute == BasicAttribute.MOVEMENT) {
            return new AttributeCalculationResult(user.getMovementPoints(), attribute);
        }

        final int racialModifier = calculatePercentageModification(user, attribute);

        final AttributeCalculationResult percentageResult = calculatePercentageModifiedAttribute(calculateActualBeforePercentageMultiplication(user, attribute), racialModifier);

        if (shouldCheckMinimum) {
            checkMinimumValue(attribute, percentageResult);
        }

        return percentageResult;
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

    public AttributeCalculationResult calculateMaximumValue(UserEntity user, Attribute attribute) {
        if (attribute.isUnlimited()) {
            return new UnlimitedAttributeCalculationResult(attribute);
        }

        final int racialModifier = calculatePercentageModification(user, attribute);

        return calculatePercentageModifiedAttribute(calculateMaximumBeforePercentageMultiplication(user, attribute), racialModifier);
    }

    public AttributeCalculationResult calculateMaximumBeforePercentageMultiplication(UserEntity userEntity, Attribute attribute) {
        final AttributeCalculationResult result = new AttributeCalculationResult(attribute);

        result.increaseValue(attribute.getInitialValue());
        if (attribute instanceof CombatAttribute) {
            result.increaseValue(combatAttributeCalculator.calculateAllBonusByGeneralAttributes(userEntity, (CombatAttribute) attribute));
        }

        for (AttributeBonusProvider attributeBonusProvider : attributeBonusProviders) {
            result.addCalculationResult(attributeBonusProvider.calculateBonus(userEntity, attribute));
        }

        return result;
    }

    public AttributeCalculationResult calculatePercentageModifiedAttribute(AttributeCalculationResult attributeValue, int percentage) {
        attributeValue.setValue((int) (attributeValue.getValue() * ((double) percentage / 100 + 1)));

        return attributeValue;
    }

    private int calculatePercentageModification(UserEntity userEntity, Attribute attribute) {
        if (attribute instanceof GeneralAttribute) {
            return getRacialModifierValue(userEntity.getRace(), (GeneralAttribute) attribute);
        }

        return 0;
    }

    private void checkMinimumValue(final Attribute attribute, final AttributeCalculationResult attributeCalculationResult) {
        if (attribute instanceof CombatAttribute) {
            final CombatAttribute combatAttribute = (CombatAttribute) attribute;

            if (combatAttribute.getMinimalValue() == 0) {
                return;
            }

            if (attributeCalculationResult.getValue() < combatAttribute.getMinimalValue()) {
                attributeCalculationResult.setValue(combatAttribute.getMinimalValue());
            }
        }
    }
}
