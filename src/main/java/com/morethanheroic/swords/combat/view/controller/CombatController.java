package com.morethanheroic.swords.combat.view.controller;

import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.service.CombatManager;
import com.morethanheroic.swords.map.repository.domain.MapObjectDatabaseEntity;
import com.morethanheroic.swords.monster.service.MonsterDefinitionManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class CombatController {

    private final CombatManager combatManager;
    private final MonsterDefinitionManager monsterDefinitionManager;

    @Autowired
    public CombatController(CombatManager combatManager, MonsterDefinitionManager monsterDefinitionManager) {
        this.combatManager = combatManager;
        this.monsterDefinitionManager = monsterDefinitionManager;
    }

    @RequestMapping(value = "/map/combat/{monsterId}", method = RequestMethod.GET)
    public Object combat(UserEntity user, @PathVariable int monsterId) {
        user.getMap().getSpawnsAt(user.getXPosition(), user.getYPosition());

        MapObjectDatabaseEntity spawn = user.getMap().getSpawnAt(user.getXPosition(), user.getYPosition(), monsterId);

        if (spawn != null) {
            //TODO: user response builder
            CombatResult result = combatManager.initiateCombat(user, monsterDefinitionManager.getMonsterDefinition(monsterId));

            if (result.getWinner() == Winner.PLAYER) {
                user.getMap().removeSpawn(spawn.getId());
            }

            return result;
        }

        HashMap<String, Object> result = new HashMap<>();

        result.put("result", "The target monster is not found.");

        return result;
        //TODO: save user health changes
    }
}
