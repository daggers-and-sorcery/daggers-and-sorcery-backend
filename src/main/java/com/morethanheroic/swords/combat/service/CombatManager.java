package com.morethanheroic.swords.combat.service;

import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.monster.service.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CombatManager {

    private final Combat combat;

    @Autowired
    public CombatManager(Combat combat) {
        this.combat = combat;
    }

    public CombatResult initiateCombat(UserEntity user, MonsterDefinition monster) {
        return combat.doFight(user, monster);
    }
}
