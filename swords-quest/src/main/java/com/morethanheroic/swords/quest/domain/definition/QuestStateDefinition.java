package com.morethanheroic.swords.quest.domain.definition;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuestStateDefinition {

    private final String description;
    private final int eventId;
    private final int eventStage;
}
