package com.morethanheroic.swords.explore.service.event.impl.sevgard.farmfields;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.OptionExplorationEventEntryResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.TextExplorationEventEntryResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.option.EventOption;
import com.morethanheroic.swords.explore.service.event.ExplorationResultFactory;
import com.morethanheroic.swords.explore.service.event.MultiStageExplorationEventDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("development") //Disabled on prod
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
                        .content("Welcoma!")
                        .build()
        ).addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("What do you want?")
                        .build()
        ).addEventEntryResult(
                OptionExplorationEventEntryResult.builder()
                        .options(Lists.newArrayList(
                                EventOption.builder()
                                        .text("Hello world")
                                        .optionId(2)
                                        .build(),
                                EventOption.builder()
                                        .text("ahoy")
                                        .optionId(3)
                                        .build()
                        ))
                        .build()
        );

        userEntity.setActiveExploration(6, 1);

        return explorationResult;
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity, int stage) {
        final ExplorationResult explorationResult = explorationResultFactory.newExplorationResult();

        if (stage == 2) {
            explorationResult.addEventEntryResult(
                    TextExplorationEventEntryResult.builder()
                            .content("Hello we are at stage 1!")
                            .build()
            ).addEventEntryResult(
                    TextExplorationEventEntryResult.builder()
                            .content("Kewl")
                            .build()
            );
        } else if (stage == 3) {
            explorationResult.addEventEntryResult(
                    TextExplorationEventEntryResult.builder()
                            .content("Hello we are at stage 2!")
                            .build()
            ).addEventEntryResult(
                    TextExplorationEventEntryResult.builder()
                            .content("Ohhyeah")
                            .build()
            );
        }

        userEntity.resetActiveExploration();

        return explorationResult;
    }

    @Override
    public ExplorationResult info(UserEntity userEntity, int stage) {
        final ExplorationResult explorationResult = explorationResultFactory.newExplorationResult();

        if (stage == 1) {
            explorationResult.addEventEntryResult(
                    TextExplorationEventEntryResult.builder()
                            .content("What do you want?")
                            .build()
            ).addEventEntryResult(
                    OptionExplorationEventEntryResult.builder()
                            .options(Lists.newArrayList(
                                    EventOption.builder()
                                            .text("Hello world")
                                            .optionId(2)
                                            .build(),
                                    EventOption.builder()
                                            .text("ahoy")
                                            .optionId(3)
                                            .build()
                            ))
                            .build()
            );
        }

        return explorationResult;
    }

    @Override
    public boolean isValidNextStageAtStage(int stage, int nextStage) {
        if (stage == 1 && (nextStage == 2 || nextStage == 3)) {
            return true;
        }

        return false;
    }
}
