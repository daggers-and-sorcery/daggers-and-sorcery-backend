package com.morethanheroic.swords.journal.view.response.quest.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class QuestJournalEntryStagePartialResponse extends PartialResponse {

    private final String description;
}
