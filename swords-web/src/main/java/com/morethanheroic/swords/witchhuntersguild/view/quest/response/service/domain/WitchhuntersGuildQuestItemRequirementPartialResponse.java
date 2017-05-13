package com.morethanheroic.swords.witchhuntersguild.view.quest.response.service.domain;

import com.morethanheroic.swords.item.view.response.service.domain.response.ItemDefinitionPartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class WitchhuntersGuildQuestItemRequirementPartialResponse extends WitchhuntersGuildQuestRequirementPartialResponse {

    private final ItemDefinitionPartialResponse item;
    private final int amount;

    @Override
    public WitchhuntersGuildQuestItemRequirementType getType() {
        return WitchhuntersGuildQuestItemRequirementType.ITEM;
    }
}
