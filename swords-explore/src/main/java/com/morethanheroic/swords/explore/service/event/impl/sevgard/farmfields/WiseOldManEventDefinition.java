package com.morethanheroic.swords.explore.service.event.impl.sevgard.farmfields;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.OptionExplorationEventEntryResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.TextExplorationEventEntryResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.option.EventOption;
import com.morethanheroic.swords.explore.service.event.ExplorationResultFactory;
import com.morethanheroic.swords.explore.service.event.impl.MultiStageExplorationEventDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WiseOldManEventDefinition extends MultiStageExplorationEventDefinition {

    @Autowired
    private ExplorationResultFactory explorationResultFactory;

    @Override
    public int getId() {
        return 6;
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity) {
        final ExplorationResult explorationResult = explorationResultFactory.newExplorationResult();

        explorationResult.addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("asd")
                        .build()
        ).addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("dsa")
                        .build()
        ).addEventEntryResult(
                OptionExplorationEventEntryResult.builder()
                        .options(Lists.newArrayList(
                                EventOption.builder()
                                        .text("Hello world")
                                        .optionId(1)
                                        .build(),
                                EventOption.builder()
                                        .text("ahoy")
                                        .optionId(2)
                                        .build()
                        ))
                        .build()
        );

        return explorationResult;
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity, int stage) {
        return null;
    }
}
