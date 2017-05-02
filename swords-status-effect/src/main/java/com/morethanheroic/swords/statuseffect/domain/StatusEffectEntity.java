package com.morethanheroic.swords.statuseffect.domain;

import com.morethanheroic.entity.domain.Entity;
import com.morethanheroic.swords.statuseffect.service.definition.domain.StatusEffectDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

/**
 * Contains the data of a status effect.
 */
@Builder
@Getter
public class StatusEffectEntity implements Entity {

    private final long id;
    private final UserEntity userEntity;
    private final StatusEffectDefinition statusEffect;
    private final Instant expirationTime;
}
