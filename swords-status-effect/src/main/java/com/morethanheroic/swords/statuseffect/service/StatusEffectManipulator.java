package com.morethanheroic.swords.statuseffect.service;

import com.morethanheroic.swords.statuseffect.repository.domain.StatusEffectMapper;
import com.morethanheroic.swords.statuseffect.service.definition.domain.StatusEffectDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * Provide various methods to manipulate status effects on an {@link com.morethanheroic.swords.user.domain.UserEntity}.
 */
@Service
@RequiredArgsConstructor
public class StatusEffectManipulator {

    private final StatusEffectMapper statusEffectMapper;

    public void applyStatusEffect(final UserEntity userEntity, final StatusEffectDefinition statusEffectDefinition, final Instant expirationTime) {
        statusEffectMapper.giveStatusEffect(userEntity.getId(), statusEffectDefinition.getId(), expirationTime);
    }
}
