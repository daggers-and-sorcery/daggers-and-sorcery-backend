package com.morethanheroic.swords.journal.repository.dao;

import com.morethanheroic.swords.journal.model.JournalType;

public class JournalDatabaseEntity {

    private int userId;
    private JournalType journalType;
    private int journalId;

    public int getUserId() {
        return userId;
    }

    public JournalType getType() {
        return journalType;
    }

    public int getJournalId() {
        return journalId;
    }
}
