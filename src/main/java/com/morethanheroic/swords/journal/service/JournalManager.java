package com.morethanheroic.swords.journal.service;

import com.morethanheroic.swords.journal.model.JournalType;
import com.morethanheroic.swords.journal.repository.domain.JournalMapper;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class JournalManager {

    private final JournalMapper journalMapper;

    public JournalManager(JournalMapper journalMapper) {
        this.journalMapper = journalMapper;
    }

    public void createJournalEntry(UserEntity userEntity, JournalType journalType, int journalEntryId) {
        journalMapper.createJournal(userEntity.getId(), journalType, journalEntryId);
    }
}
