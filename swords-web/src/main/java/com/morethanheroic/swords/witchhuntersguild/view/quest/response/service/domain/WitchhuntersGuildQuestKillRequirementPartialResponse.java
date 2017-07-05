package com.morethanheroic.swords.witchhuntersguild.view.quest.response.service.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class WitchhuntersGuildQuestKillRequirementPartialResponse extends WitchhuntersGuildQuestRequirementPartialResponse {

    private final String monsterName;
    private final int amount;
    private final int done;

    @Override
    public WitchhuntersGuildQuestItemRequirementType getType() {
        return WitchhuntersGuildQuestItemRequirementType.KILL;
    }
}
