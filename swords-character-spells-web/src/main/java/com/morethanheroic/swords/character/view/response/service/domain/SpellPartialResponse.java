package com.morethanheroic.swords.character.view.response.service.domain;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.character.view.response.service.cost.domain.CastingCostPartialResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.Collection;

@Getter
@Builder
public class SpellPartialResponse extends PartialResponse {

    private final int id;
    private final String name;
    private final String description;
    private final boolean combatSpell;
    private final String type;
    private final Collection<? extends CastingCostPartialResponse> castingCost;
    private final boolean openPage;
}
