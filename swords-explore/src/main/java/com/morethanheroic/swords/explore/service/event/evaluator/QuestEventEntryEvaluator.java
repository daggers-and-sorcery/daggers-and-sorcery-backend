package com.morethanheroic.swords.explore.service.event.evaluator;

import com.morethanheroic.swords.explore.domain.event.result.impl.QuestExplorationEventEntryResult;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class QuestEventEntryEvaluator {

    private static final Locale DEFAULT_LOCALE = Locale.ENGLISH;

    private final MessageSource messageSource;

    public QuestExplorationEventEntryResult questEntry(final String questName, final String questDescription, final int acceptQuestStage, final int declineQuestStage, final Object... args) {
        return QuestExplorationEventEntryResult.builder()
                .name(messageSource.getMessage(questName, args, DEFAULT_LOCALE))
                .description(messageSource.getMessage(questDescription, args, DEFAULT_LOCALE))
                .acceptQuestStage(acceptQuestStage)
                .declineQuestStage(declineQuestStage)
                .build();
    }
}
