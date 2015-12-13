package com.morethanheroic.swords.attribute.service.calc;

import com.google.common.collect.ImmutableMap;
import com.morethanheroic.swords.attribute.domain.*;
import com.morethanheroic.swords.attribute.service.AttributeToRacialModifierConverter;
import com.morethanheroic.swords.attribute.service.calc.domain.AttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.AttributeData;
import com.morethanheroic.swords.attribute.service.calc.domain.UnlimitedAttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.equipment.EquipmentAttributeBonusCalculator;
import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.race.model.RaceDefinition;
import com.morethanheroic.swords.race.model.modifier.RacialModifier;
import com.morethanheroic.swords.race.model.modifier.entry.NumericRacialModifierEntry;
import com.morethanheroic.swords.race.service.RaceDefinitionCache;
import com.morethanheroic.swords.skill.service.SkillManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
public class GlobalAttributeCalculator implements AttributeCalculator {

    @Autowired
    private SkillAttributeCalculator skillAttributeCalculator;

    @Autowired
    private GeneralAttributeCalculator generalAttributeCalculator;

    @Autowired
    private CombatAttributeCalculator combatAttributeCalculator;

    @Autowired
    private BasicAttributeCalculator basicAttributeCalculator;

    @Autowired
    private EquipmentAttributeBonusCalculator equipmentAttributeBonusCalculator;

    @Autowired
    private SkillManager skillManager;

    @Autowired
    private AttributeToRacialModifierConverter attributeToRacialModifierConverter;

    @Autowired
    private RaceDefinitionCache raceDefinitionCache;

    private Map<Class<? extends Attribute>, AttributeCalculator> attributeCalculatorMap;

    @PostConstruct
    public void initialize() {
        attributeCalculatorMap = ImmutableMap.<Class<? extends Attribute>, AttributeCalculator>of(
                SkillAttribute.class, skillAttributeCalculator,
                GeneralAttribute.class, generalAttributeCalculator,
                CombatAttribute.class, combatAttributeCalculator,
                BasicAttribute.class, basicAttributeCalculator
        );
    }

    @SuppressWarnings("unchecked")
    public AttributeData calculateAttributeValue(UserEntity user, Attribute attribute) {
        return attributeCalculatorMap.get(attribute.getClass()).calculateAttributeValue(user, attribute);
    }

    public AttributeCalculationResult calculateActualBeforePercentageMultiplication(UserEntity user, Attribute attribute) {
        AttributeCalculationResult result = new AttributeCalculationResult(attribute);

        if (attribute instanceof SkillAttribute) {
            result.increaseValue(skillManager.getSkills(user).getSkillLevel((SkillAttribute) attribute));
        } else if (attribute instanceof GeneralAttribute) {
            result.increaseValue(attribute.getInitialValue());
            result.increaseValue(generalAttributeCalculator.calculatePointsBonusBySkills(user, attribute));
        } else if (attribute instanceof CombatAttribute) {
            result.increaseValue(attribute.getInitialValue());
            result.increaseValue(combatAttributeCalculator.calculateAllBonusByGeneralAttributes(user, (CombatAttribute) attribute));
        } else {
            result.increaseValue(attribute.getInitialValue());
        }

        result.addCalculationResult(equipmentAttributeBonusCalculator.calculateEquipmentBonus(user, attribute));

        return result;
    }

    public AttributeCalculationResult calculateActualValue(UserEntity user, Attribute attribute) {
        if (attribute == CombatAttribute.LIFE) {
            return new AttributeCalculationResult(user.getHealthPoints(), attribute);
        } else if (attribute == CombatAttribute.MANA) {
            return new AttributeCalculationResult(user.getManaPoints(), attribute);
        } else if (attribute == BasicAttribute.MOVEMENT) {
            return new AttributeCalculationResult(user.getMovementPoints(), attribute);
        }

        int racialModifier = 0;
        if (attribute instanceof  GeneralAttribute) {
            racialModifier = getRacialModifierValue(user.getRace(), (GeneralAttribute) attribute);
        }

        return calculatePercentageModifiedAttribute(calculateActualBeforePercentageMultiplication(user, attribute), racialModifier);
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

        int racialModifier = 0;
        if (attribute instanceof  GeneralAttribute) {
            racialModifier = getRacialModifierValue(user.getRace(), (GeneralAttribute) attribute);
        }

        return calculatePercentageModifiedAttribute(calculateMaximumBeforePercentageMultiplication(user, attribute), racialModifier);
    }

    public AttributeCalculationResult calculateMaximumBeforePercentageMultiplication(UserEntity userEntity, Attribute attribute) {
        AttributeCalculationResult result = new AttributeCalculationResult(attribute);

        result.increaseValue(attribute.getInitialValue());
        if (attribute instanceof CombatAttribute) {
            result.increaseValue(combatAttributeCalculator.calculateAllBonusByGeneralAttributes(userEntity, (CombatAttribute) attribute));
        }
        result.addCalculationResult(equipmentAttributeBonusCalculator.calculateEquipmentBonus(userEntity, attribute));

        return result;
    }

    public AttributeCalculationResult calculatePercentageModifiedAttribute(AttributeCalculationResult attributeValue, int percentage) {

        attributeValue.setValue((int) (attributeValue.getValue() * ((double) percentage / 100 + 1)));

        return attributeValue;
    }
}
