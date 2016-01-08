package com.morethanheroic.swords.journal.service;

import com.morethanheroic.swords.journal.model.JournalType;
import com.morethanheroic.swords.journal.repository.dao.JournalDatabaseEntity;
import com.morethanheroic.swords.journal.repository.domain.JournalMapper;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalManager {

    private final JournalMapper journalMapper;

    @Autowired
    public JournalManager(JournalMapper journalMapper) {
        this.journalMapper = journalMapper;
    }

    public void createJournalEntry(UserEntity userEntity, JournalType journalType, int journalEntryId) {
        journalMapper.createJournal(userEntity.getId(), journalType, journalEntryId);
    }

    public List<JournalDatabaseEntity> getJournalEntryListByType(UserEntity userEntity, JournalType journalType) {
        return journalMapper.getJournalEntryListByType(userEntity.getId(), journalType);
    }

    public boolean hasJournal(UserEntity userEntity, JournalType journalType, int journalEntryId) {
        return journalMapper.getJournal(userEntity.getId(), journalType, journalEntryId) != null;
    }
}
