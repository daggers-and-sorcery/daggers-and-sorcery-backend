package com.morethanheroic.swords.combat.view.response.service.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CombatAttackStatusPartialResponse extends PartialResponse {

    private final boolean combatEnded;
}
