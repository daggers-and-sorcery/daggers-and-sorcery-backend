package com.morethanheroic.swords.combat.view.controller;

import com.morethanheroic.swords.combat.service.CombatManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CombatController {

    private final CombatManager combatManager;

    @Autowired
    public CombatController(CombatManager combatManager) {
        this.combatManager = combatManager;
    }

    @RequestMapping(value = "/map/combat/{monsterId}", method = RequestMethod.GET)
    public Object combat(UserEntity user, @PathVariable int monsterId) {
        return combatManager.initiateCombat(user, monsterId);
    }
}
