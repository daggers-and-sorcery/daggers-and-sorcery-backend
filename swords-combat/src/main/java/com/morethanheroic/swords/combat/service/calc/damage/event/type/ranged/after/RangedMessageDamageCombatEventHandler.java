package com.morethanheroic.swords.combat.service.calc.damage.event.type.ranged.after;

import com.morethanheroic.swords.combat.entity.domain.MonsterCombatEntity;
import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
import com.morethanheroic.swords.combat.service.CombatStepListBuilder;
import com.morethanheroic.swords.combat.service.calc.attack.type.ranged.AmmunitionLossCalculator;
import com.morethanheroic.swords.combat.service.event.damage.after.AfterDamageCombatEventHandler;
import com.morethanheroic.swords.combat.service.event.damage.after.domain.AfterDamageCombatEventContext;
import com.morethanheroic.swords.combat.service.event.damage.after.domain.AfterDamageCombatEventResult;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageType;
import com.morethanheroic.swords.combat.step.domain.DefaultCombatStep;
import com.morethanheroic.swords.combat.step.message.CombatMessageFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Order(200)
@Service
@RequiredArgsConstructor
public class RangedMessageDamageCombatEventHandler implements AfterDamageCombatEventHandler {

    private final CombatMessageFactory combatMessageFactory;
    private final AmmunitionLossCalculator ammunitionLossCalculator;

    @Override
    public Optional<AfterDamageCombatEventResult> handleEvent(final AfterDamageCombatEventContext afterDamageCombatEventContext) {
        if (afterDamageCombatEventContext.getDamageType() == DamageType.RANGED) {
            if (afterDamageCombatEventContext.getAttacker() instanceof MonsterCombatEntity) {
                return Optional.of(
                        AfterDamageCombatEventResult.builder()
                                .combatSteps(
                                        CombatStepListBuilder.builder()
                                                .withCombatStep(
                                                        DefaultCombatStep.builder()
                                                                .message(combatMessageFactory.newMessage("damage_gained", "COMBAT_MESSAGE_RANGED_DAMAGE_TO_PLAYER", afterDamageCombatEventContext.getAttacker().getName(), afterDamageCombatEventContext.getDamageDone()))
                                                                .build()
                                                )
                                                .build()
                                )
                                .build()
                );
            } else {
                return Optional.of(
                        AfterDamageCombatEventResult.builder()
                                .combatSteps(
                                        CombatStepListBuilder.builder()
                                                .withCombatStep(
                                                        DefaultCombatStep.builder()
                                                                .message(combatMessageFactory.newMessage("damage_gained", "COMBAT_MESSAGE_RANGED_DAMAGE_TO_MONSTER", afterDamageCombatEventContext.getDefender().getName(), afterDamageCombatEventContext.getDamageDone()))
                                                                .build()
                                                )
                                                .withCombatSteps(
                                                        ammunitionLossCalculator.calculateAmmunitionLoss((UserCombatEntity) afterDamageCombatEventContext.getAttacker())
                                                )
                                                .build()
                                )
                                .build()
                );
            }
        }

        return Optional.empty();
    }
}
