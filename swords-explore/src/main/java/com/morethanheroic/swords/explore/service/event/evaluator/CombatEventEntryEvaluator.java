package com.morethanheroic.swords.explore.service.event.evaluator;

import com.google.common.collect.ImmutableList;
import com.morethanheroic.swords.combat.domain.AttackResult;
import com.morethanheroic.swords.combat.domain.CombatType;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.service.create.CreateCombatCalculator;
import com.morethanheroic.swords.combat.service.create.domain.CombatCreationContext;
import com.morethanheroic.swords.explore.domain.event.result.impl.CombatExplorationEventEntryResult;
import com.morethanheroic.swords.explore.service.event.evaluator.domain.CombatEventEntryEvaluatorResult;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.monster.service.cache.MonsterDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;

@Service
@RequiredArgsConstructor
public class CombatEventEntryEvaluator {

    private final MonsterDefinitionCache monsterDefinitionCache;
    private final CreateCombatCalculator createCombatCalculator;
    private final Random random;

    public MonsterDefinition calculateOpponent(final List<MonsterDefinition> possibleOpponents) {
        return possibleOpponents.get(random.nextInt(possibleOpponents.size()));
    }

    public CombatEventEntryEvaluatorResult calculateCombat(final UserEntity userEntity, final MonsterDefinition opponent, final CombatType combatType) {
        final AttackResult combatResult = createCombatCalculator.createCombat(
                CombatCreationContext.builder()
                        .userEntity(userEntity)
                        .monsterDefinition(opponent)
                        .type(combatType)
                        .build()
        );

        return CombatEventEntryEvaluatorResult.builder()
                .result(
                        CombatExplorationEventEntryResult.builder()
                                .combatSteps(combatResult.getAttackResult())
                                .combatEnded(combatResult.isCombatEnded())
                                .playerDead(combatResult.getWinner() == Winner.MONSTER)
                                .build()
                )
                .build();
    }

    public List<MonsterDefinition> convertMonsterIdToDefinition(final List<Integer> ids) {
        return ids.stream()
                .map(this::convertMonsterIdToDefinition)
                .collect(collectingAndThen(Collectors.toList(), ImmutableList::copyOf));
    }

    public MonsterDefinition convertMonsterIdToDefinition(final int id) {
        return monsterDefinitionCache.getDefinition(id);
    }
}
