package com.morethanheroic.swords.explore.service.event;

import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.service.calc.CombatCalculator;
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
public class CombatEvaluator {

    @Autowired
    private CombatCalculator combatCalculator;

    @Autowired
    private MonsterDefinitionCache monsterDefinitionCache;

    @Autowired
    private Random random;

    public CombatResult calculateCombatWithRandomOpponent(final UserEntity userEntity, final List<MonsterDefinition> opponent) {
        return combatCalculator.doFight(userEntity, calculateOpponent(opponent));
    }

    public MonsterDefinition calculateOpponent(final List<MonsterDefinition> possibleOpponents) {
        return possibleOpponents.get(random.nextInt(possibleOpponents.size()));
    }

    public CombatResult calculateCombat(final UserEntity userEntity, final MonsterDefinition opponent) {
        return combatCalculator.doFight(userEntity, opponent);
    }

    public List<MonsterDefinition> convertMonsterIdToDefinition(final List<Integer> ids) {
        return Collections.unmodifiableList(ids.stream().map(this::convertMonsterIdToDefinition).collect(Collectors.toList()));
    }

    public MonsterDefinition convertMonsterIdToDefinition(final int id) {
        return monsterDefinitionCache.getMonsterDefinition(id);
    }
}
