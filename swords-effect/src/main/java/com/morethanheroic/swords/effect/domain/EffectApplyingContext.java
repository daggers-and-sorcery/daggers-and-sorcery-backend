package com.morethanheroic.swords.effect.domain;

import lombok.Builder;
import lombok.Getter;

/**
 * Context used when applying an effect.
 */
@Builder
@Getter
public class EffectApplyingContext {

    private EffectTarget source;
    private EffectTarget destination;
}
