package com.morethanheroic.swords.journal.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.journal.domain.JournalType;
import com.morethanheroic.swords.journal.service.JournalEntityFactory;
import com.morethanheroic.swords.journal.view.response.JournalEntryResponseBuilder;
import com.morethanheroic.swords.journal.view.response.JournalListResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JournalController {

    private final JournalEntityFactory journalManager;
    private final JournalListResponseBuilder journalListResponseBuilder;
    private final JournalEntryResponseBuilder journalEntryResponseBuilder;

    @GetMapping("/journal/list/{journal_type}")
    public Response listJournal(UserEntity userEntity, @PathVariable("journal_type") JournalType journalType) {
        return journalListResponseBuilder.build(userEntity, journalType);
    }

    @GetMapping("/journal/entry/{journal_type}/{journal_id}")
    public Response journalEntry(UserEntity userEntity, @PathVariable("journal_type") JournalType journalType, @PathVariable("journal_id") int journalId) {
        if (journalManager.hasJournal(userEntity, journalType, journalId)) {
            return journalEntryResponseBuilder.build(userEntity, journalType, journalId);
        } else {
            return journalEntryResponseBuilder.buildInvalidRequest(userEntity);
        }
    }
}
