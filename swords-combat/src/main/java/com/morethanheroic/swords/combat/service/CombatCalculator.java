package com.morethanheroic.swords.combat.service;

import com.morethanheroic.swords.combat.repository.domain.CombatExperienceMapper;
import com.morethanheroic.swords.combat.repository.domain.CombatMapper;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.monster.service.cache.MonsterDefinitionCache;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CombatCalculator {

    private final CombatMapper combatMapper;
    private final CombatExperienceMapper combatExperienceMapper;
    private final MonsterDefinitionCache monsterDefinitionCache;

    @Transactional
    public boolean isCombatRunning(final UserEntity userEntity) {
        return combatMapper.getRunningCombat(userEntity.getId()) != null;
    }

    //TODO: move away
    @Transactional
    public void addCombatExperience(final UserEntity userEntity, final SkillType skillType, final int amount) {
        combatExperienceMapper.addExperience(userEntity.getId(), skillType, amount);
    }

    @Transactional
    public MonsterDefinition getOpponentInRunningCombat(UserEntity userEntity) {
        return monsterDefinitionCache.getDefinition(combatMapper.getRunningCombat(userEntity.getId()).getMonsterId());
    }

    public void removeAllCombatForUser(final UserEntity userEntity) {
        combatMapper.removeCombatForUser(userEntity.getId());
    }
}
