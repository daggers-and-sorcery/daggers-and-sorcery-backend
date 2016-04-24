package com.morethanheroic.swords.combat.service.calc;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class CombatCalculator {

    private final CombatFactory combatFactory;
    private final CombatInitializer combatInitializer;
    private final CombatFightCalculator combatFightCalculator;
    private final CombatTerminator combatTerminator;

    @Transactional
    public CombatResult doFight(UserEntity userEntity, MonsterDefinition monsterDefinition) {
        Combat combat = combatFactory.getCombat(userEntity, monsterDefinition);
        CombatResult combatResult = new CombatResult();

        combatInitializer.initialize(combat, combatResult);
        combatFightCalculator.fight(combat, combatResult);
        combatTerminator.terminate(combat, combatResult);

        return combatResult;
    }
}
