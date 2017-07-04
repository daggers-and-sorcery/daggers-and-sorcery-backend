package com.morethanheroic.swords.combat.entity;

import com.morethanheroic.swords.combat.entity.domain.CombatEntity;
import com.morethanheroic.swords.combat.entity.domain.MonsterCombatEntity;
import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
import org.springframework.stereotype.Service;

/**
 * Provide basic utilities for working with {@link CombatEntity}es.
 */
@Service
public class CombatEntityUtil {

    public boolean isPlayer(final CombatEntity combatEntity) {
        return combatEntity instanceof UserCombatEntity;
    }

    public boolean isMonster(final CombatEntity combatEntity) {
        return combatEntity instanceof MonsterCombatEntity;
    }
}
