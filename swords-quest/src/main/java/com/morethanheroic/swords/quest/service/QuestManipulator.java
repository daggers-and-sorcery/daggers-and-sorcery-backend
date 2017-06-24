package com.morethanheroic.swords.quest.service;

import com.morethanheroic.swords.quest.domain.definition.QuestDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Provides entry point for manipulating the quest related properties of the user.
 */
@Service
@RequiredArgsConstructor
public class QuestManipulator {

    private static final int STARTING_QUEST_STAGE = 1;

    private final QuestStateCalculator questStateCalculator;

    public void startQuest(final UserEntity userEntity, final QuestDefinition questDefinition) {
        questStateCalculator.setQuestStage(userEntity, questDefinition, STARTING_QUEST_STAGE);
    }

    public void changeQuestStage(final UserEntity userEntity, final QuestDefinition questDefinition, final int stage) {
        questStateCalculator.setQuestStage(userEntity, questDefinition, stage);
    }
}
