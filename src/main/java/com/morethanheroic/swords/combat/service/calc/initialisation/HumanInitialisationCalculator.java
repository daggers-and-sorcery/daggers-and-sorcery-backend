package com.morethanheroic.swords.combat.service.calc.initialisation;

import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.combat.service.CombatUtil;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class HumanInitialisationCalculator {

    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final CombatUtil combatUtil;
    private final Random random;

    @Autowired
    public HumanInitialisationCalculator(GlobalAttributeCalculator globalAttributeCalculator, CombatUtil combatUtil, Random random) {
        this.globalAttributeCalculator = globalAttributeCalculator;
        this.combatUtil = combatUtil;
        this.random = random;
    }

    public int calculateInitialisation(UserEntity user) {
        return globalAttributeCalculator.calculateActualValue(user, CombatAttribute.INITIATION) + random.nextInt(combatUtil.getUserWeaponSkillLevel(user));
    }
}
