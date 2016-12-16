package com.morethanheroic.swords.combat.service.attack;

import com.morethanheroic.swords.combat.domain.AttackResult;
import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.domain.SavedCombatEntity;
import com.morethanheroic.swords.combat.domain.entity.MonsterCombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.repository.domain.CombatMapper;
import com.morethanheroic.swords.combat.service.SavedCombatEntityFactory;
import com.morethanheroic.swords.combat.service.calc.CombatEntityType;
import com.morethanheroic.swords.combat.service.calc.CombatTerminator;
import com.morethanheroic.swords.combat.service.calc.initialisation.InitialisationCalculator;
import com.morethanheroic.swords.combat.service.context.CombatContextFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttackCombatCalculator {

    private final InitialisationCalculator initialisationCalculator;
    private final PlayerAttackCalculator playerAttackCalculator;
    private final MonsterAttackCalculator monsterAttackCalculator;
    private final SavedCombatEntityFactory savedCombatEntityFactory;
    private final CombatContextFactory combatContextFactory;
    private final CombatTerminator combatTerminator;
    private final CombatMapper combatMapper;

    @Transactional
    public AttackResult attack(final UserEntity userEntity) {
        final SavedCombatEntity savedCombatEntity = savedCombatEntityFactory.getEntity(userEntity);
        final CombatContext combatContext = combatContextFactory.newContext(savedCombatEntity);

        final List<CombatStep> combatSteps = new ArrayList<>();

        if (initialisationCalculator.calculateInitialisation(combatContext) == CombatEntityType.MONSTER) {
            combatSteps.addAll(monsterAttackCalculator.monsterAttack(combatContext));

            if (combatContext.getUser().getActualHealth() > 0) {
                combatSteps.addAll(playerAttackCalculator.playerAttack(combatContext));
            }
        } else {
            combatSteps.addAll(playerAttackCalculator.playerAttack(combatContext));

            if (combatContext.getOpponent().getActualHealth() > 0) {
                combatSteps.addAll(monsterAttackCalculator.monsterAttack(combatContext));
            }
        }

        final UserCombatEntity userCombatEntity = combatContext.getUser();
        userEntity.setBasicStats(userCombatEntity.getActualHealth(), userCombatEntity.getActualMana(), userCombatEntity.getUserEntity().getMovementPoints());

        if (combatContext.getWinner() != null) {
            combatSteps.addAll(combatTerminator.terminate(combatContext));

            combatMapper.removeCombat(savedCombatEntity.getId());
        } else {
            final MonsterCombatEntity monsterCombatEntity = combatContext.getOpponent();

            combatMapper.updateCombat(savedCombatEntity.getId(), monsterCombatEntity.getActualHealth(), monsterCombatEntity.getActualMana());
        }

        return AttackResult.builder()
                .attackResult(combatSteps)
                .combatEnded(combatContext.getWinner() != null)
                .winner(combatContext.getWinner())
                .build();
    }
}
