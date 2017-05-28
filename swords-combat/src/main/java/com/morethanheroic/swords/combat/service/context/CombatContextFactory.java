package com.morethanheroic.swords.combat.service.context;

import com.morethanheroic.swords.combat.domain.CombatType;
import com.morethanheroic.swords.combat.domain.SavedCombatEntityFactoryContext;
import com.morethanheroic.swords.combat.service.SavedCombatEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
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
    private final SavedCombatEntityFactory savedCombatEntityFactory;

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

    public CombatContext newContext(final UserEntity userEntity, final CombatType combatType) {
        return newContext(
                savedCombatEntityFactory.getEntity(
                        SavedCombatEntityFactoryContext.builder()
                                .userEntity(userEntity)
                                .combatType(combatType)
                                .build()
                )
        );
    }
}
