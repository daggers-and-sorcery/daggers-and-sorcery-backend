package com.morethanheroic.swords.journal.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.journal.view.response.quest.QuestJournalListResponseBuilder;
import com.morethanheroic.swords.journal.view.response.quest.domain.QuestJournalListResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A controller for the quest functionality in the journal.
 */
@RestController
@RequiredArgsConstructor
public class QuestJournalController {

    private final QuestJournalListResponseBuilder questJournalListResponseBuilder;

    @GetMapping("/journal/list/quest")
    public Response listJournal(final UserEntity userEntity) {
        return questJournalListResponseBuilder.build(
                QuestJournalListResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .build()
        );
    }
}
