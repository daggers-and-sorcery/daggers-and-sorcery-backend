package com.morethanheroic.swords.combat.domain;

import com.morethanheroic.session.domain.SessionEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

/**
 * Holds additional data for the effect like the effect's target etc.
 */
@Getter
@RequiredArgsConstructor
public class CombatEffectDataHolder {

    private final Map<String, Object> parameters;
    private final SessionEntity sessionEntity;
}
