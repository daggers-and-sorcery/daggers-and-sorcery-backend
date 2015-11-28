package com.morethanheroic.swords.combat.service;

import com.morethanheroic.swords.combat.domain.CombatMessage;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.service.calc.CombatCalculator;
import com.morethanheroic.swords.map.domain.MapEntity;
import com.morethanheroic.swords.map.repository.domain.MapObjectDatabaseEntity;
import com.morethanheroic.swords.map.service.MapManager;
import com.morethanheroic.swords.monster.service.cache.MonsterDefinitionCache;
import com.morethanheroic.swords.movement.domain.MovementEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CombatManager {

    private final CombatCalculator combatCalculator;
    private final MonsterDefinitionCache monsterDefinitionCache;
    private final MapManager mapManager;

    @Autowired
    public CombatManager(CombatCalculator combatCalculator, MonsterDefinitionCache monsterDefinitionCache, MapManager mapManager) {
        this.combatCalculator = combatCalculator;
        this.monsterDefinitionCache = monsterDefinitionCache;
        this.mapManager = mapManager;
    }

    public CombatResult initiateCombat(UserEntity user, int monsterId) {
        MovementEntity movementEntity = user.getMovement();

        MapObjectDatabaseEntity spawn = mapManager.getMap(movementEntity.getMap().getId()).getSpawnAt(movementEntity.getX(), movementEntity.getY(), monsterId);

        if (spawn != null) {
            return combatCalculator.doFight(user, monsterDefinitionCache.getMonsterDefinition(monsterId), spawn);
        } else {
            //TODO: do this better, with real error handling on user side
            CombatResult combatResult = new CombatResult();

            CombatMessage combatMessage = new CombatMessage();
            combatMessage.addData("result", "The target monster is not found.");

            combatResult.addMessage(combatMessage);

            return combatResult;
        }
    }
}
