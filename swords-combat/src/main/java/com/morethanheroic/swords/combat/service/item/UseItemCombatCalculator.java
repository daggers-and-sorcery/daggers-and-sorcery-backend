package com.morethanheroic.swords.combat.service.item;

import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.combat.domain.AttackResult;
import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.service.CombatEffectDataHolderFactory;
import com.morethanheroic.swords.combat.service.CombatTeardownCalculator;
import com.morethanheroic.swords.combat.service.attack.MonsterAttackCalculator;
import com.morethanheroic.swords.combat.service.calc.CombatEntityType;
import com.morethanheroic.swords.combat.service.calc.initialisation.InitialisationCalculator;
import com.morethanheroic.swords.combat.service.context.CombatContextFactory;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UseItemCombatCalculator {

    private final InitialisationCalculator initialisationCalculator;
    private final MonsterAttackCalculator monsterAttackCalculator;
    private final UseItemService useItemService;
    private final CombatContextFactory combatContextFactory;
    private final CombatEffectDataHolderFactory combatEffectDataHolderFactory;
    private final CombatTeardownCalculator combatTeardownCalculator;

    @Transactional
    public AttackResult useItem(final CombatContext combatContext, final SessionEntity sessionEntity, final ItemDefinition itemDefinition) {
        final List<CombatStep> combatSteps = new ArrayList<>();
        if (initialisationCalculator.calculateInitialisation(combatContext) == CombatEntityType.MONSTER) {
            combatSteps.addAll(monsterAttackCalculator.monsterAttack(combatContext));

            if (combatContext.getUser().getActualHealth() > 0) {
                combatSteps.addAll(
                        useItemService.useItem(combatContext.getUser(), itemDefinition, combatEffectDataHolderFactory.newDataHolder(sessionEntity))
                );
            }
        } else {
            combatSteps.addAll(
                    useItemService.useItem(combatContext.getUser(), itemDefinition, combatEffectDataHolderFactory.newDataHolder(sessionEntity))
            );

            if (combatContext.getOpponent().getActualHealth() > 0) {
                combatSteps.addAll(monsterAttackCalculator.monsterAttack(combatContext));
            }
        }

        combatSteps.addAll(combatTeardownCalculator.teardown(combatContext));

        return AttackResult.builder()
                .attackResult(combatSteps)
                .combatEnded(combatContext.getWinner() != null)
                .winner(combatContext.getWinner())
                .build();
    }
}
