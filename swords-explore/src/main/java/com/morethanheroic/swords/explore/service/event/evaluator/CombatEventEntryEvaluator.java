package com.morethanheroic.swords.explore.service.event.evaluator;

import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.domain.AttackResult;
import com.morethanheroic.swords.combat.service.create.CreateCombatCalculator;
import com.morethanheroic.swords.explore.domain.event.result.impl.CombatExplorationEventEntryResult;
import com.morethanheroic.swords.explore.service.event.evaluator.domain.CombatEventEntryEvaluatorResult;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.monster.service.cache.MonsterDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CombatEventEntryEvaluator {

    @Autowired
    private MonsterDefinitionCache monsterDefinitionCache;

    @Autowired
    private Random random;

    @Autowired
    private CreateCombatCalculator createCombatCalculator;

    public MonsterDefinition calculateOpponent(final List<MonsterDefinition> possibleOpponents) {
        return possibleOpponents.get(random.nextInt(possibleOpponents.size()));
    }

    public CombatEventEntryEvaluatorResult calculateCombat(final UserEntity userEntity, final MonsterDefinition opponent) {
        final AttackResult combatResult = createCombatCalculator.createCombat(userEntity, opponent);

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
        return Collections.unmodifiableList(ids.stream().map(this::convertMonsterIdToDefinition).collect(Collectors.toList()));
    }

    public MonsterDefinition convertMonsterIdToDefinition(final int id) {
        return monsterDefinitionCache.getMonsterDefinition(id);
    }
}
