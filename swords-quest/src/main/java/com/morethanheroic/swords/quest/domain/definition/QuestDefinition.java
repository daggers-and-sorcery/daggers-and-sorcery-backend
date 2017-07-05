package com.morethanheroic.swords.quest.domain.definition;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class QuestDefinition {

    private final int id;
    private final String name;
    private final String description;
    //TODO: Rename to stages and QuestStageDefinition
    private final List<QuestStateDefinition> states;
    private final List<String> rewards;

    public int getCompletedAtStage() {
        return states.size();
    }
}
