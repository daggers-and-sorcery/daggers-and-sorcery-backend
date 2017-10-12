package com.morethanheroic.swords.combat.service;

import com.morethanheroic.swords.combat.domain.CombatType;
import com.morethanheroic.swords.combat.repository.domain.CombatExperienceMapper;
import com.morethanheroic.swords.combat.repository.domain.CombatMapper;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.monster.service.cache.MonsterDefinitionCache;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CombatCalculator {

    private final CombatMapper combatMapper;
    private final CombatExperienceMapper combatExperienceMapper;
    private final MonsterDefinitionCache monsterDefinitionCache;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public boolean isCombatRunning(final UserEntity userEntity, final CombatType combatType) {
        return combatMapper.getRunningCombat(userEntity.getId(), combatType) != null;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void addCombatExperience(final UserEntity userEntity, final SkillType skillType, final int amount) {
        combatExperienceMapper.addExperience(userEntity.getId(), skillType, amount);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public MonsterDefinition getOpponentInRunningCombat(UserEntity userEntity, final CombatType combatType) {
        return monsterDefinitionCache.getDefinition(combatMapper.getRunningCombat(userEntity.getId(), combatType).getMonsterId());
    }

    public void removeAllCombatForUser(final UserEntity userEntity) {
        combatMapper.removeExplorationCombatForUser(userEntity.getId());
    }
}
