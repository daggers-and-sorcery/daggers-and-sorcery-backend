package com.morethanheroic.swords.combat.view.controller;

import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.service.CombatManager;
import com.morethanheroic.swords.monster.service.MonsterDefinitionManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CombatController {

    private final CombatManager combatManager;
    private final MonsterDefinitionManager monsterDefinitionManager;

    @Autowired
    public CombatController(CombatManager combatManager, MonsterDefinitionManager monsterDefinitionManager) {
        this.combatManager = combatManager;
        this.monsterDefinitionManager = monsterDefinitionManager;
    }

    @RequestMapping(value="/map/combat/{monsterId}", method = RequestMethod.GET)
    public CombatResult combat(UserEntity user, @PathVariable int monsterId) {
        //TODO: user response buiklder
        //TODO: check if monster exists on map and remove if killed

        return combatManager.initiateCombat(user, monsterDefinitionManager.getMonsterDefinition(monsterId));
        //TODO: save user health changes
    }
}
