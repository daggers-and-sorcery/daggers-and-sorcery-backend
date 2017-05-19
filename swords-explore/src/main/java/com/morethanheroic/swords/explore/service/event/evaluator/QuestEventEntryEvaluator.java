package com.morethanheroic.swords.explore.service.event.evaluator;

import com.morethanheroic.swords.explore.domain.event.result.impl.QuestExplorationEventEntryResult;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class QuestEventEntryEvaluator {

    public QuestExplorationEventEntryResult questEntry(final String questName, final String questDescription, final int acceptQuestStage, final int declineQuestStage) {
        return QuestExplorationEventEntryResult.builder()
                .name(questName)
                .description(questDescription)
                .acceptQuestStage(acceptQuestStage)
                .declineQuestStage(declineQuestStage)
                .build();
    }
}
