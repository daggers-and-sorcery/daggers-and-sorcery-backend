package com.morethanheroic.swords.quest.domain;

import com.morethanheroic.entity.domain.Entity;
import com.morethanheroic.swords.quest.domain.definition.QuestDefinition;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class QuestEntity implements Entity {

    private final int stage;
    private final QuestState state;
    private final QuestDefinition questDefinition;

    @Override
    public int getId() {
        return questDefinition.getId();
    }
}
