package com.morethanheroic.swords.journal.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.journal.view.response.quest.QuestJournalEntryResponseBuilder;
import com.morethanheroic.swords.journal.view.response.quest.domain.QuestJournalEntryResponseBuilderConfiguration;
import com.morethanheroic.swords.quest.domain.definition.QuestDefinition;
import com.morethanheroic.swords.quest.service.QuestStateCalculator;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuestJournalEntryController {

    private final QuestStateCalculator questStateCalculator;
    private final QuestJournalEntryResponseBuilder questJournalEntryResponseBuilder;

    /**
     * Handle the journal entry requests for quest entries.
     *
     * @param userEntity      the user who are requesting
     * @param questDefinition the quest's definition he's requests
     * @return the response for the request
     */
    @GetMapping("/journal/entry/quest/{questId}")
    public Response journalEntry(final UserEntity userEntity, final @PathVariable("questId") QuestDefinition questDefinition) {
        if (!questStateCalculator.isStarted(userEntity, questDefinition)) {
            throw new IllegalArgumentException("The required quest is not started by the user!");
        }

        return questJournalEntryResponseBuilder.build(
                QuestJournalEntryResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .questDefinition(questDefinition)
                        .build()
        );
    }
}
