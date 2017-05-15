package com.morethanheroic.swords.quest.domain.definition;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuestDefinition {

    private final int id;
    private final String name;
    private final String definition;
}
