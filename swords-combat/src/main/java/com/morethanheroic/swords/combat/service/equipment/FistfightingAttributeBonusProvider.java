package com.morethanheroic.swords.combat.service.equipment;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.service.bonus.AttributeBonusProvider;
import com.morethanheroic.swords.attribute.service.calc.AttributeCalculationResultFactory;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.DiceValueAttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.SimpleValueAttributeCalculationResult;
import com.morethanheroic.swords.combat.service.CombatUtil;
import com.morethanheroic.swords.configuration.domain.Configuration;
import com.morethanheroic.swords.configuration.service.definition.cache.ConfigurationDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FistfightingAttributeBonusProvider implements AttributeBonusProvider {

    private final CombatUtil combatUtil;
    private final AttributeCalculationResultFactory attributeCalculationResultFactory;
    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final ConfigurationDefinitionCache configurationDefinitionCache;

    @Override
    public Optional<SimpleValueAttributeCalculationResult> calculateBonus(final UserEntity userEntity, final Attribute attribute) {
        if (combatUtil.isFistfighting(userEntity)) {
            final SimpleValueAttributeCalculationResult result = attributeCalculationResultFactory.newResult(attribute);

            if (result.getAttribute() == CombatAttribute.ATTACK) {
                ((DiceValueAttributeCalculationResult) result).increaseD4(1 + (int) Math.floor(getFistfightingLevelOrMaximumBonusValue(userEntity) / 4));
            } else if (result.getAttribute() == CombatAttribute.DAMAGE) {
                ((DiceValueAttributeCalculationResult) result).increaseD2(1 + (int) Math.floor(getFistfightingLevelOrMaximumBonusValue(userEntity) / 4));
            }

            return Optional.of(result);
        }

        return Optional.empty();
    }

    private int getFistfightingLevelOrMaximumBonusValue(final UserEntity userEntity) {
        final int fistfightingLevel = globalAttributeCalculator.calculateActualValue(userEntity, SkillAttribute.FISTFIGHT).getValue();
        final int maximumBonus = configurationDefinitionCache.getDefinition(Configuration.MAXIMUM_FISTFIGHTING_DAMAGE_MODIFIER).getValue();

        return fistfightingLevel > maximumBonus ? maximumBonus : fistfightingLevel;
    }
}
