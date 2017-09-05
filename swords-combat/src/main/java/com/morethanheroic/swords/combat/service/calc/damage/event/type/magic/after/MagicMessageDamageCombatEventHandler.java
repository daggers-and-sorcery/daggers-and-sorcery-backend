package com.morethanheroic.swords.combat.service.calc.damage.event.type.magic.after;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.combat.entity.domain.MonsterCombatEntity;
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
public class MagicMessageDamageCombatEventHandler implements AfterDamageCombatEventHandler {

    private final CombatMessageFactory combatMessageFactory;

    @Override
    public Optional<AfterDamageCombatEventResult> handleEvent(AfterDamageCombatEventContext afterDamageCombatEventContext) {
        if (afterDamageCombatEventContext.getDamageType() == DamageType.MAGIC) {
            if (afterDamageCombatEventContext.getAttacker() instanceof MonsterCombatEntity) {
                return Optional.of(
                        AfterDamageCombatEventResult.builder()
                                .combatSteps(
                                        Lists.newArrayList(
                                                DefaultCombatStep.builder()
                                                        .message(combatMessageFactory.newMessage("damage_gained", "COMBAT_MESSAGE_MAGIC_DAMAGE_TO_PLAYER", afterDamageCombatEventContext.getAttacker().getName(), afterDamageCombatEventContext.getDamageDone()))
                                                        .build()
                                        )
                                )
                                .build()
                );
            } else {
                return Optional.of(
                        AfterDamageCombatEventResult.builder()
                                .combatSteps(
                                        Lists.newArrayList(
                                                DefaultCombatStep.builder()
                                                        .message(combatMessageFactory.newMessage("damage_done", "COMBAT_MESSAGE_MAGIC_DAMAGE_TO_MONSTER", afterDamageCombatEventContext.getDefender().getName(), afterDamageCombatEventContext.getDamageDone()))
                                                        .build()
                                        )
                                )
                                .build()
                );
            }
        }

        return Optional.empty();
    }
}
