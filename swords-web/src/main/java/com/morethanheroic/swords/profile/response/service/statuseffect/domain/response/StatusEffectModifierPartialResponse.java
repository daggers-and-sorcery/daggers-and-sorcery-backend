package com.morethanheroic.swords.profile.response.service.statuseffect.domain.response;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.statuseffect.service.definition.domain.StatusEffectModifier;

import lombok.Builder;
import lombok.Getter;

/**
 * Build a response for a {@link com.morethanheroic.swords.statuseffect.service.definition.domain.StatusEffectModifier};
 */
@Getter
@Builder
public class StatusEffectModifierPartialResponse extends PartialResponse {

    private final String modifier;
    private final int amount;
    private final int d2;
    private final int d4;
    private final int d6;
    private final int d8;
    private final int d10;
}
