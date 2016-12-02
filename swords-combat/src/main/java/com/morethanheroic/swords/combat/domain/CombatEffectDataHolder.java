package com.morethanheroic.swords.combat.domain;

import java.util.Map;

import com.morethanheroic.session.domain.SessionEntity;

import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Holds additional data for the effect like the effect's target etc.
 *
 * @deprecated Everything available here is already available in the {@link CombatEffectApplyingContext}.
 */
@Getter
@RequiredArgsConstructor
@Deprecated
public class CombatEffectDataHolder {

    private final Map<String, Object> parameters;
    private final SessionEntity sessionEntity;
}
