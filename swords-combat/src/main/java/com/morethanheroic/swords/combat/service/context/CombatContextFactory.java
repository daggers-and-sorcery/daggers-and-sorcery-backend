package com.morethanheroic.swords.combat.service.context;

import org.springframework.stereotype.Service;

import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.domain.SavedCombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.service.MonsterCombatEntityFactory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CombatContextFactory {

    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final MonsterCombatEntityFactory monsterCombatEntityFactory;

    public CombatContext newContext(final SavedCombatEntity savedCombatEntity) {
        return CombatContext.builder()
             .user(new UserCombatEntity(savedCombatEntity.getUser(), globalAttributeCalculator))
             .opponent(monsterCombatEntityFactory.newMonsterCombatEntity(
                 savedCombatEntity.getMonster(),
                 savedCombatEntity.getMonsterHealth(),
                 savedCombatEntity.getMonsterMana())
             )
            .combatId(savedCombatEntity.getId())
             .build();
    }
}
