package com.morethanheroic.swords.combat.service.calc;

import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class CombatFactory {

    private final GlobalAttributeCalculator globalAttributeCalculator;

    public Combat getCombat(UserEntity userEntity, MonsterDefinition monsterDefinition) {
        return new Combat(userEntity, monsterDefinition, globalAttributeCalculator);
    }
}
