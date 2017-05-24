package com.morethanheroic.swords.journal.view.response.quest.domain;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.quest.domain.QuestState;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class QuestJournalListEntryPartialResponse extends PartialResponse {

    private final int id;
    private final String name;
    private final QuestState questState;
}
