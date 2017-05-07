package com.morethanheroic.swords.statuseffect.service;

import com.morethanheroic.swords.statuseffect.domain.StatusEffectEntity;
import com.morethanheroic.swords.statuseffect.repository.dao.StatusEffectDatabaseEntity;
import com.morethanheroic.swords.statuseffect.service.definition.cache.StatusEffectDefinitionCache;
import com.morethanheroic.swords.user.service.UserEntityFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatusEffectEntityTransformer {

    private final UserEntityFactory userEntityFactory;
    private final StatusEffectDefinitionCache statusEffectDefinitionCache;

    public StatusEffectEntity transform(final StatusEffectDatabaseEntity statusEffectDatabaseEntity) {
        return StatusEffectEntity.builder()
                .id(statusEffectDatabaseEntity.getId())
                .userEntity(userEntityFactory.getEntity(statusEffectDatabaseEntity.getUserId()))
                .statusEffect(statusEffectDefinitionCache.getDefinition(statusEffectDatabaseEntity.getStatusEffectId()))
                .expirationTime(statusEffectDatabaseEntity.getExpirationTime())
                .build();
    }
}
