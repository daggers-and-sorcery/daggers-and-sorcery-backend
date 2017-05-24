package com.morethanheroic.swords.journal.view.response.quest;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.journal.view.response.quest.domain.QuestJournalListEntryPartialResponse;
import com.morethanheroic.swords.journal.view.response.quest.domain.QuestJournalListResponseBuilderConfiguration;
import com.morethanheroic.swords.quest.domain.QuestEntity;
import com.morethanheroic.swords.quest.service.QuestListEntityFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestJournalListEntryPartialResponseBuilder implements PartialResponseCollectionBuilder<QuestJournalListResponseBuilderConfiguration> {

    private final QuestListEntityFactory questListEntityFactory;

    @Override
    public Collection<? extends PartialResponse> build(QuestJournalListResponseBuilderConfiguration questJournalListResponseBuilderConfiguration) {
        return questListEntityFactory.getEntity(questJournalListResponseBuilderConfiguration.getUserEntity()).stream()
                .filter(QuestEntity::isStarted)
                .map(questEntity ->
                        QuestJournalListEntryPartialResponse.builder()
                                .id(questEntity.getQuestDefinition().getId())
                                .name(questEntity.getQuestDefinition().getName())
                                .description(questEntity.getQuestDefinition().getDescription())
                                .questState(questEntity.getState().getName())
                                .build()
                )
                .collect(Collectors.toList());
    }
}
