package com.morethanheroic.swords.journal.repository.dao;

import com.morethanheroic.swords.journal.model.JournalType;

public class JournalDatabaseEntity {

    private int user_id;
    private JournalType type;
    private int journal_id;

    public int getUserId() {
        return user_id;
    }

    public JournalType getType() {
        return type;
    }

    public int getJournalId() {
        return journal_id;
    }
}
