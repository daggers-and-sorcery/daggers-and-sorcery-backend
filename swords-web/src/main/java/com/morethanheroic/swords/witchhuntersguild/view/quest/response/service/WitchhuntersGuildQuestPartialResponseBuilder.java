package com.morethanheroic.swords.witchhuntersguild.view.quest.response.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.witchhuntersguild.domain.definition.WitchhuntersGuildJobDefinition;
import com.morethanheroic.swords.witchhuntersguild.view.quest.response.service.domain.WitchhuntersGuildQuestPartialResponse;
import com.morethanheroic.swords.witchhuntersguild.view.quest.response.service.domain.WitchhuntersGuildQuestResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WitchhuntersGuildQuestPartialResponseBuilder implements PartialResponseBuilder<WitchhuntersGuildQuestResponseBuilderConfiguration> {

    private final WitchhuntersGuildQuestRequirementPartialResponseBuilder witchhuntersGuildQuestRequirementPartialResponseBuilder;

    @Override
    public PartialResponse build(WitchhuntersGuildQuestResponseBuilderConfiguration witchhuntersGuildQuestResponseBuilderConfiguration) {
        final WitchhuntersGuildJobDefinition jobDefinition = witchhuntersGuildQuestResponseBuilderConfiguration.getJobDefinition();

        return WitchhuntersGuildQuestPartialResponse.builder()
                .name(jobDefinition.getName())
                .description(jobDefinition.getDescription())
                .reward(jobDefinition.getReward())
                .requirements(witchhuntersGuildQuestRequirementPartialResponseBuilder.build(witchhuntersGuildQuestResponseBuilderConfiguration))
                .build();
    }
}
