package com.morethanheroic.swords.combat.view.response.service.domain;

import com.morethanheroic.response.domain.PartialResponse;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CombatStepPartialResponse extends PartialResponse {

    private final String icon;
    private final String message;
}
