package com.morethanheroic.swords.combat.service.calc.damage.event.type.melee.after;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.combat.entity.domain.MonsterCombatEntity;
import com.morethanheroic.swords.combat.service.event.damage.after.AfterDamageCombatEventHandler;
import com.morethanheroic.swords.combat.service.event.damage.after.domain.AfterDamageCombatEventContext;
import com.morethanheroic.swords.combat.service.event.damage.after.domain.AfterDamageCombatEventResult;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageType;
import com.morethanheroic.swords.combat.step.domain.DefaultCombatStep;
import com.morethanheroic.swords.combat.step.message.CombatMessageFactory;
import com.morethanheroic.swords.combat.step.message.domain.CombatMessageContext;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.monster.domain.MonsterType;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Order(200)
@Service
@RequiredArgsConstructor
public class MeleeMessageDamageCombatEventHandler implements AfterDamageCombatEventHandler {

    private final CombatMessageFactory combatMessageFactory;
    private final Random random;

    @Override
    public Optional<AfterDamageCombatEventResult> handleEvent(AfterDamageCombatEventContext afterDamageCombatEventContext) {
        if (afterDamageCombatEventContext.getDamageType() == DamageType.MELEE) {
            if (afterDamageCombatEventContext.getAttacker() instanceof MonsterCombatEntity) {
                final MonsterDefinition monsterDefinition = ((MonsterCombatEntity) afterDamageCombatEventContext.getAttacker()).getMonsterDefinition();
                final CombatMessageContext combatMessageContext = CombatMessageContext.builder()
                        .type(monsterDefinition.getType())
                        .subtype(monsterDefinition.getSubtype())
                        .weaponType(monsterDefinition.getWeaponType())
                        .build();

                return Optional.of(
                        AfterDamageCombatEventResult.builder()
                                .combatSteps(
                                        Lists.newArrayList(
                                                DefaultCombatStep.builder()
                                                        .message(
                                                                combatMessageFactory.newMessage(
                                                                        "damage_gained",
                                                                        "COMBAT_MESSAGE_MELEE_DAMAGE_TO_PLAYER",
                                                                        combatMessageContext,
                                                                        afterDamageCombatEventContext.getAttacker().getName(),
                                                                        afterDamageCombatEventContext.getDamageDone(),
                                                                        getRandomBodySlot(combatMessageContext)
                                                                )
                                                        )
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
                                                        .message(combatMessageFactory.newMessage("damage_done", "COMBAT_MESSAGE_MELEE_DAMAGE_TO_MONSTER", afterDamageCombatEventContext.getDefender().getName(), afterDamageCombatEventContext.getDamageDone()))
                                                        .build()
                                        )
                                )
                                .build()
                );
            }
        }

        return Optional.empty();
    }

    private String getRandomBodySlot(final CombatMessageContext messageContext) {
        if (messageContext.getType() == MonsterType.UNDEAD && messageContext.getSubtype() == MonsterType.SKELETON) {
            final String[] slots = new String[]{"stomach", "arm", "shoulder", "knee", "thigh"};

            return slots[random.nextInt(slots.length)];
        }

        return "";
    }
}
