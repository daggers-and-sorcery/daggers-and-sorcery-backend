package com.morethanheroic.swords.quest.domain;

import com.morethanheroic.entity.domain.Entity;
import com.morethanheroic.swords.quest.domain.definition.QuestDefinition;
import com.morethanheroic.swords.quest.domain.definition.QuestStateDefinition;
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

    public QuestStateDefinition getDefinitionForActiveStage() {
        return questDefinition.getStates().get(stage);
    }

    public boolean isStarted() {
        return state != QuestState.NOT_STARTED;
    }

    public boolean isCompleted() {
        return state == QuestState.COMPLETED;
    }
}
