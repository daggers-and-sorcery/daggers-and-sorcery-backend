package com.morethanheroic.swords.witchhuntersguild.view.quest.response.service.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class WitchhuntersGuildQuestPartialResponse extends PartialResponse {

    private final String name;
    private final String description;
    private final int reward;
    private final List<WitchhuntersGuildQuestRequirementPartialResponse> requirements;
}
