package com.morethanheroic.swords.combat.service;

import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.service.calc.CombatCalculator;
import com.morethanheroic.swords.monster.service.cache.MonsterDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CombatManager {

    private final CombatCalculator combatCalculator;
    private final MonsterDefinitionCache monsterDefinitionCache;

    @Autowired
    public CombatManager(CombatCalculator combatCalculator, MonsterDefinitionCache monsterDefinitionCache) {
        this.combatCalculator = combatCalculator;
        this.monsterDefinitionCache = monsterDefinitionCache;
    }

    public CombatResult initiateCombat(UserEntity user, int monsterId) {
        return combatCalculator.doFight(user, monsterDefinitionCache.getMonsterDefinition(monsterId));
    }
}
