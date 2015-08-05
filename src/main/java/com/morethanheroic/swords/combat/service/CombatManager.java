package com.morethanheroic.swords.combat.service;

import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.monster.service.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CombatManager {

    private final CombatMessageBuilder combatMessageBuilder;
    private final GlobalAttributeCalculator globalAttributeCalculator;

    @Autowired
    public CombatManager(CombatMessageBuilder combatMessageBuilder, GlobalAttributeCalculator globalAttributeCalculator) {
        this.combatMessageBuilder = combatMessageBuilder;
        this.globalAttributeCalculator = globalAttributeCalculator;
    }

    public CombatResult initiateCombat(UserEntity user, MonsterDefinition monster) {
        Combat combat = new Combat(user, monster, combatMessageBuilder, globalAttributeCalculator);

        return combat.doFight();
    }
}
