package com.morethanheroic.swords.quest.domain;

import com.morethanheroic.entity.domain.Entity;
import com.morethanheroic.swords.quest.domain.definition.QuestDefinition;
import com.morethanheroic.swords.quest.domain.definition.QuestStateDefinition;
import lombok.Builder;
import lombok.Getter;

/**
 * Contains the data of a quest.
 */
@Builder
@Getter
public class QuestEntity implements Entity {

    /**
     * This prefix is needed because the not started quest stage is 0 and we don't want to create empty definitions for
     * "not started" quests stages and the List's counting is tarted at 0.
     */
    private static final int STARTING_QUEST_PREFIX = 1;

    private final int stage;
    private final QuestState state;
    private final QuestDefinition questDefinition;

    @Override
    public int getId() {
        return questDefinition.getId();
    }

    /**
     * Return the definition for the active stage of the quest.
     *
     * @return the stage definition for the active stage
     */
    public QuestStateDefinition getDefinitionForActiveStage() {
        return questDefinition.getStates().get(stage - STARTING_QUEST_PREFIX);
    }

    public boolean isStarted() {
        return state != QuestState.NOT_STARTED;
    }

    public boolean isCompleted() {
        return state == QuestState.COMPLETED;
    }
}
