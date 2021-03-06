package com.morethanheroic.swords.combat.service;

import com.morethanheroic.entity.service.factory.EntityFactory;
import com.morethanheroic.swords.combat.domain.SavedCombatEntity;
import com.morethanheroic.swords.combat.domain.SavedCombatEntityFactoryContext;
import com.morethanheroic.swords.combat.repository.dao.CombatDatabaseEntity;
import com.morethanheroic.swords.combat.repository.domain.CombatMapper;
import com.morethanheroic.swords.combat.service.exception.IllegalCombatStateException;
import com.morethanheroic.swords.monster.service.cache.MonsterDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SavedCombatEntityFactory implements EntityFactory<SavedCombatEntity, SavedCombatEntityFactoryContext> {

    private final CombatMapper combatMapper;
    private final MonsterDefinitionCache monsterDefinitionCache;

    @Override
    public SavedCombatEntity getEntity(final SavedCombatEntityFactoryContext combatEntityFactoryContext) {
        final UserEntity userEntity = combatEntityFactoryContext.getUserEntity();

        final CombatDatabaseEntity combatDatabaseEntity = combatMapper.getRunningCombat(userEntity.getId(), combatEntityFactoryContext.getCombatType());

        if (combatDatabaseEntity == null) {
            throw new IllegalCombatStateException(
                    "Error while creating SavedCombatEntity for user " + userEntity + ". His event data is event: "
                            + userEntity.getActiveExplorationEvent() + " state: " + userEntity.getActiveExplorationState() + ".");
        }

        return SavedCombatEntity.builder()
                .id(combatDatabaseEntity.getId())
                .user(userEntity)
                .monster(monsterDefinitionCache.getDefinition(combatDatabaseEntity.getMonsterId()))
                .monsterHealth(combatDatabaseEntity.getMonsterHealth())
                .monsterMana(combatDatabaseEntity.getMonsterMana())
                .build();
    }
}
