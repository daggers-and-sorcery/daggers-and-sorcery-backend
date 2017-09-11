package com.morethanheroic.swords.combat.domain.effect.entry;

import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.service.calc.domain.data.AttributeData;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.ImprovedCombatEffectDefinition;
import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
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
    private final GlobalAttributeCalculator globalAttributeCalculator;

    @Override
    public void apply(final CombatEffectApplyingContext effectApplyingContext) {
        final int healAmount = effectApplyingContext.getEffectSettings().getSettingAsInt("heal-amount");
        final int damageAmount = effectApplyingContext.getEffectSettings().getSettingAsInt("damage-amount");

        final UserCombatEntity userCombatEntity = (UserCombatEntity) effectApplyingContext.getDestination().getCombatEntity();

        if (vampireCalculator.isVampire(userCombatEntity.getUserEntity())) {
            userCombatEntity.increaseActualHealth(healAmount);
        } else {
            final AttributeData attributeData = globalAttributeCalculator.calculateAttributeValue(userCombatEntity.getUserEntity(), CombatAttribute.LIFE);

            if (attributeData.getActual().getValue() - damageAmount <= MINIMUM_HEALTH) {
                userCombatEntity.decreaseActualHealth(MINIMUM_HEALTH);
            } else {
                userCombatEntity.decreaseActualHealth(damageAmount);
            }
        }
    }

    @Override
    public String getId() {
        return "heal_vampire_damage_human";
    }
}
