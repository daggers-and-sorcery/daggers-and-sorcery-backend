package com.morethanheroic.swords.combat.domain.effect.entry;

import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.service.calc.domain.data.AttributeData;
import com.morethanheroic.swords.attribute.service.manipulator.UserBasicAttributeManipulator;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.ImprovedCombatEffectDefinition;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.vampire.service.VampireCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Heals vampire characters only. If the player is not a vampire he will be damaged but can't be killed by the damage of
 * this effect.
 */

@Service
@RequiredArgsConstructor
public class HealVampireEffectDefinition extends ImprovedCombatEffectDefinition {

    private static final int MINIMUM_HEALTH = 1;

    private final VampireCalculator vampireCalculator;
    private final UserBasicAttributeManipulator basicAttributeManipulator;
    private final GlobalAttributeCalculator globalAttributeCalculator;

    @Override
    public void apply(final CombatEffectApplyingContext effectApplyingContext) {
        final int healAmount = effectApplyingContext.getEffectSettings().getSettingAsInt("heal-amount");
        final int damageAmount = effectApplyingContext.getEffectSettings().getSettingAsInt("damage-amount");

        final UserEntity userEntity = ((UserCombatEntity) effectApplyingContext.getDestination().getCombatEntity()).getUserEntity();

        if (vampireCalculator.isVampire(userEntity)) {
            basicAttributeManipulator.increaseHealth(userEntity, healAmount);
        } else {
            final AttributeData attributeData = globalAttributeCalculator.calculateAttributeValue(userEntity, CombatAttribute.LIFE);

            if (attributeData.getActual().getValue() - damageAmount <= MINIMUM_HEALTH) {
                basicAttributeManipulator.decreaseHealth(userEntity, MINIMUM_HEALTH);
            } else {
                basicAttributeManipulator.decreaseHealth(userEntity, damageAmount);
            }
        }
    }

    @Override
    public String getId() {
        return "heal_vampire";
    }
}
