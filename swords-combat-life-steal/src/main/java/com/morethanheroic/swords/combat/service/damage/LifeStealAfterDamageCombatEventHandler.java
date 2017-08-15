package com.morethanheroic.swords.combat.service.damage;

import java.util.Optional;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.attribute.domain.SpecialAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.combat.entity.domain.CombatEntity;
import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
import com.morethanheroic.swords.combat.service.event.CombatEventHandler;
import com.morethanheroic.swords.combat.service.event.damage.after.AfterDamageCombatEventHandler;
import com.morethanheroic.swords.combat.service.event.damage.after.domain.AfterDamageCombatEventContext;
import com.morethanheroic.swords.combat.service.event.damage.after.domain.AfterDamageCombatEventResult;
import com.morethanheroic.swords.combat.step.domain.DefaultCombatStep;
import com.morethanheroic.swords.combat.step.message.CombatMessageFactory;
import lombok.RequiredArgsConstructor;

@CombatEventHandler
@RequiredArgsConstructor
public class LifeStealAfterDamageCombatEventHandler implements AfterDamageCombatEventHandler {

    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final CombatMessageFactory combatMessageFactory;

    @Override
    public Optional<AfterDamageCombatEventResult> handleEvent(final AfterDamageCombatEventContext afterDamageCombatEventContext) {
        final int healedAmount = calculateHealedAmount(afterDamageCombatEventContext);

        afterDamageCombatEventContext.getAttacker().increaseActualHealth(healedAmount);

        return Optional.of(
                AfterDamageCombatEventResult.builder()
                        .combatSteps(
                                Lists.newArrayList(
                                        DefaultCombatStep.builder()
                                                .message(combatMessageFactory.newMessage("lifesteal", "COMBAT_MESSAGE_LIFE_STEAL_HEAL", healedAmount))
                                                .build()
                                )
                        )
                        .build()
        );
    }

    private int calculateHealedAmount(final AfterDamageCombatEventContext afterDamageCombatEventContext) {
        final int lifeStealAttributeValue = calculateLifeSteal(afterDamageCombatEventContext.getAttacker());

        return afterDamageCombatEventContext.getDamageDone() > lifeStealAttributeValue ? lifeStealAttributeValue : afterDamageCombatEventContext.getDamageDone();
    }

    private int calculateLifeSteal(final CombatEntity combatEntity) {
        if (combatEntity instanceof UserCombatEntity) {
            return globalAttributeCalculator.calculateActualValue(((UserCombatEntity) combatEntity).getUserEntity(), SpecialAttribute.LIFE_STEAL).getValue();
        }

        return 0;
    }
}
