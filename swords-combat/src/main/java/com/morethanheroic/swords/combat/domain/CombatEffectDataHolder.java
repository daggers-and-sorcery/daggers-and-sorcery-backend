package com.morethanheroic.swords.combat.domain;

import java.util.Map;

import com.morethanheroic.session.domain.SessionEntity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Holds additional data for the effect like the effect's target etc.
 */
//TODO: Deprecate this and move the necessary things to the applying context!
@Getter
@RequiredArgsConstructor
public class CombatEffectDataHolder {

    private final Map<String, Object> parameters;
    private final SessionEntity sessionEntity;
}
