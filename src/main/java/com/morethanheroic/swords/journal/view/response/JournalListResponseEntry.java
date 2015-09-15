package com.morethanheroic.swords.journal.view.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class JournalListResponseEntry {

    private final int entryId;
    private final String entryName;

    public JournalListResponseEntry(int entryId, String entryName) {
        this.entryId = entryId;
        this.entryName = entryName;
    }
}
