package com.morethanheroic.swords.journal.view.response.quest.domain;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.quest.domain.QuestState;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class QuestJournalEntryPartialResponse extends PartialResponse {

    private final int id;
    private final String name;
    private final String description;
    private final QuestState state;
    private final List<QuestJournalEntryStagePartialResponse> stages;
}
