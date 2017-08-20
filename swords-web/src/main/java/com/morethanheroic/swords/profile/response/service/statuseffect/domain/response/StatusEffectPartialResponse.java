package com.morethanheroic.swords.profile.response.service.statuseffect.domain.response;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

/**
 * A {@link PartialResponse} about a status effect.
 */
@Getter
@Builder
public class StatusEffectPartialResponse extends PartialResponse {

    private final int id;
    private final String name;
    private final String description;
    private final Instant expirationTime;
    private final List<? extends PartialResponse> modifiers;
}
