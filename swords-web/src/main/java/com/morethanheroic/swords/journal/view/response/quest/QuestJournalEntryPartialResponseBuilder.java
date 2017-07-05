package com.morethanheroic.swords.journal.view.response.quest;

import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.journal.view.response.quest.domain.QuestJournalEntryPartialResponse;
import com.morethanheroic.swords.journal.view.response.quest.domain.QuestJournalEntryResponseBuilderConfiguration;
import com.morethanheroic.swords.journal.view.response.quest.domain.QuestJournalEntryStagePartialResponse;
import com.morethanheroic.swords.quest.domain.definition.QuestDefinition;
import com.morethanheroic.swords.quest.service.QuestStateCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class QuestJournalEntryPartialResponseBuilder implements PartialResponseBuilder<QuestJournalEntryResponseBuilderConfiguration> {

    private final QuestStateCalculator questStateCalculator;

    @Override
    public QuestJournalEntryPartialResponse build(final QuestJournalEntryResponseBuilderConfiguration questJournalEntryResponseBuilderConfiguration) {
        final QuestDefinition questDefinition = questJournalEntryResponseBuilderConfiguration.getQuestDefinition();

        return QuestJournalEntryPartialResponse.builder()
                .id(questDefinition.getId())
                .name(questDefinition.getName())
                .description(questDefinition.getDescription())
                .state(questStateCalculator.getQuestState(questJournalEntryResponseBuilderConfiguration.getUserEntity(), questDefinition))
                .stages(buildStages(questJournalEntryResponseBuilderConfiguration))
                .build();
    }

    private List<QuestJournalEntryStagePartialResponse> buildStages(final QuestJournalEntryResponseBuilderConfiguration questJournalEntryResponseBuilderConfiguration) {
        return IntStream.range(0, questStateCalculator.getQuestStage(questJournalEntryResponseBuilderConfiguration.getUserEntity(), questJournalEntryResponseBuilderConfiguration.getQuestDefinition()))
                .mapToObj(i -> questJournalEntryResponseBuilderConfiguration.getQuestDefinition().getStates().get(i))
                .map(questStateDefinition ->
                        QuestJournalEntryStagePartialResponse.builder()
                                .description(questStateDefinition.getDescription())
                                .build()
                )
                .collect(Collectors.toList());
    }
}
