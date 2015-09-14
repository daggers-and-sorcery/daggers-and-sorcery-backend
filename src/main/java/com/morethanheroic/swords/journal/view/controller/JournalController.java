package com.morethanheroic.swords.journal.view.controller;

import com.morethanheroic.swords.common.response.Response;
import com.morethanheroic.swords.journal.model.JournalType;
import com.morethanheroic.swords.journal.service.JournalEntryResponseBuilder;
import com.morethanheroic.swords.journal.service.JournalListResponseBuilder;
import com.morethanheroic.swords.journal.service.JournalManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JournalController {

    private final JournalManager journalManager;
    private final JournalListResponseBuilder journalListResponseBuilder;
    private final JournalEntryResponseBuilder journalEntryResponseBuilder;

    @Autowired
    public JournalController(JournalManager journalManager, JournalListResponseBuilder journalListResponseBuilder, JournalEntryResponseBuilder journalEntryResponseBuilder) {
        this.journalManager = journalManager;
        this.journalListResponseBuilder = journalListResponseBuilder;
        this.journalEntryResponseBuilder = journalEntryResponseBuilder;
    }

    @RequestMapping(value = "/journal/list/{journal_type}", method = RequestMethod.GET)
    public Response listJournal(UserEntity userEntity, @PathVariable("journal_type") JournalType journalType) {
        return journalListResponseBuilder.build(userEntity, journalType);
    }

    @RequestMapping(value = "/journal/entry/{journal_type}/{journal_id}", method = RequestMethod.GET)
    public Response journalEntry(UserEntity userEntity, @PathVariable("journal_type") JournalType journalType, @PathVariable("journal_id") int journalId) {
        if (journalManager.hasJournal(userEntity, journalType, journalId)) {
            return journalEntryResponseBuilder.build(userEntity, journalType, journalId);
        } else {
            return journalEntryResponseBuilder.buildInvalidRequest(userEntity);
        }
    }
}
