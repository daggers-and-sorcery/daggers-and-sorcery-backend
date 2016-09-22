package com.morethanheroic.swords.combat.service.spell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.combat.domain.AttackResult;
import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.SavedCombatEntity;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.domain.entity.MonsterCombatEntity;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.domain.step.DefaultCombatStep;
import com.morethanheroic.swords.combat.service.CombatTeardownCalculator;
import com.morethanheroic.swords.combat.service.SavedCombatEntityFactory;
import com.morethanheroic.swords.combat.service.attack.MonsterAttackCalculator;
import com.morethanheroic.swords.combat.service.calc.CombatEntityType;
import com.morethanheroic.swords.combat.service.calc.initialisation.InitialisationCalculator;
import com.morethanheroic.swords.combat.service.context.CombatContextFactory;
import com.morethanheroic.swords.spell.domain.SpellDefinition;
import com.morethanheroic.swords.spell.service.UseSpellService;
import com.morethanheroic.swords.user.domain.UserEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UseSpellCombatCalculator {

    private final InitialisationCalculator initialisationCalculator;
    private final SavedCombatEntityFactory savedCombatEntityFactory;
    private final CombatContextFactory combatContextFactory;
    private final CombatTeardownCalculator combatTeardownCalculator;
    private final MonsterAttackCalculator monsterAttackCalculator;
    private final UseSpellService useSpellService;

    @Transactional
    public AttackResult useSpell(final UserEntity userEntity, final SessionEntity sessionEntity, final SpellDefinition spellDefinition) {
        final SavedCombatEntity savedCombatEntity = savedCombatEntityFactory.getEntity(userEntity.getId());
        final CombatContext combatContext = combatContextFactory.newContext(savedCombatEntity);

        final CombatEffectDataHolder combatEffectDataHolder = new CombatEffectDataHolder(new HashMap<>(), sessionEntity);

        final List<CombatStep> combatSteps = new ArrayList<>();

        if (initialisationCalculator.calculateInitialisation(combatContext) == CombatEntityType.MONSTER) {
            combatSteps.addAll(monsterAttackCalculator.monsterAttack(combatContext));

            if (combatContext.getUser().getActualHealth() > 0) {
                combatSteps.addAll(
                    useSpellService.useSpell(combatContext, spellDefinition, combatEffectDataHolder)
                );
            }
        } else {
            combatSteps.addAll(
                useSpellService.useSpell(combatContext, spellDefinition, combatEffectDataHolder)
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
