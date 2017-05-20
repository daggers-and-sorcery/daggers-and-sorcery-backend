package com.morethanheroic.swords.journal.view.response.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JournalListResponseEntry {

    private final int entryId;
    private final String entryName;
}
