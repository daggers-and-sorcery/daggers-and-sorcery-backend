package com.morethanheroic.swords.journal.service;

import com.morethanheroic.swords.journal.domain.JournalType;
import com.morethanheroic.swords.journal.repository.dao.JournalDatabaseEntity;
import com.morethanheroic.swords.journal.repository.domain.JournalMapper;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JournalEntityFactory {

    private final JournalMapper journalMapper;

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
