package com.morethanheroic.swords.witchhuntersguild.view.quest.response.service;

import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.item.view.response.service.IdentifiedItemPartialResponseBuilder;
import com.morethanheroic.swords.item.view.response.service.domain.configuration.IdentifiedItemPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.witchhuntersguild.domain.definition.requirement.WitchhuntersGuildJobItemRequirement;
import com.morethanheroic.swords.witchhuntersguild.domain.definition.requirement.WitchhuntersGuildJobKillRequirement;
import com.morethanheroic.swords.witchhuntersguild.domain.definition.requirement.WitchhuntersGuildJobRequirement;
import com.morethanheroic.swords.witchhuntersguild.view.quest.response.service.domain.WitchhuntersGuildQuestItemRequirementPartialResponse;
import com.morethanheroic.swords.witchhuntersguild.view.quest.response.service.domain.WitchhuntersGuildQuestKillRequirementPartialResponse;
import com.morethanheroic.swords.witchhuntersguild.view.quest.response.service.domain.WitchhuntersGuildQuestRequirementPartialResponse;
import com.morethanheroic.swords.witchhuntersguild.view.quest.response.service.domain.WitchhuntersGuildQuestResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WitchhuntersGuildQuestRequirementPartialResponseBuilder implements PartialResponseCollectionBuilder<WitchhuntersGuildQuestResponseBuilderConfiguration> {

    private final IdentifiedItemPartialResponseBuilder identifiedItemPartialResponseBuilder;

    @Override
    public List<WitchhuntersGuildQuestRequirementPartialResponse> build(final WitchhuntersGuildQuestResponseBuilderConfiguration witchhuntersGuildQuestResponseBuilderConfiguration) {
        return witchhuntersGuildQuestResponseBuilderConfiguration.getJobDefinition().getRequirements().stream()
                .map(this::build)
                .collect(Collectors.toList());
    }

    private WitchhuntersGuildQuestRequirementPartialResponse build(final WitchhuntersGuildJobRequirement witchhuntersGuildJobRequirement) {
        //TODO: Use different builders!
        if (witchhuntersGuildJobRequirement instanceof WitchhuntersGuildJobItemRequirement) {
            final WitchhuntersGuildJobItemRequirement witchhuntersGuildJobItemRequirement = (WitchhuntersGuildJobItemRequirement) witchhuntersGuildJobRequirement;

            return WitchhuntersGuildQuestItemRequirementPartialResponse.builder()
                    .item(identifiedItemPartialResponseBuilder.build(
                            IdentifiedItemPartialResponseBuilderConfiguration.builder()
                                    .item(witchhuntersGuildJobItemRequirement.getItem())
                                    .build()
                    ))
                    .amount(witchhuntersGuildJobItemRequirement.getAmount())
                    .build();
        } else if (witchhuntersGuildJobRequirement instanceof WitchhuntersGuildJobKillRequirement) {
            final WitchhuntersGuildJobKillRequirement witchhuntersGuildJobKillRequirement = (WitchhuntersGuildJobKillRequirement) witchhuntersGuildJobRequirement;

            return WitchhuntersGuildQuestKillRequirementPartialResponse.builder()
                    .monsterName(witchhuntersGuildJobKillRequirement.getMonster().getName())
                    .amount(witchhuntersGuildJobKillRequirement.getAmount())
                    .build();
        } else {
            throw new IllegalStateException("Unknown Witchhunter guild quest requirement: " + witchhuntersGuildJobRequirement);
        }
    }
}
