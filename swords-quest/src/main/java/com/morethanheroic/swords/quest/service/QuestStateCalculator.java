package com.morethanheroic.swords.quest.service;

import com.morethanheroic.swords.metadata.service.MetadataEntityFactory;
import com.morethanheroic.swords.quest.domain.QuestState;
import com.morethanheroic.swords.quest.domain.definition.QuestDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Calculate the actual state of a quest.
 */
@Service
@RequiredArgsConstructor
public class QuestStateCalculator {

    private final MetadataEntityFactory metadataEntityFactory;

    public boolean isStarted(final UserEntity userEntity, final QuestDefinition questDefinition) {
        return getQuestStage(userEntity, questDefinition) > 0;
    }

    public boolean isCompleted(final UserEntity userEntity, final QuestDefinition questDefinition) {
        return getQuestStage(userEntity, questDefinition) == questDefinition.getCompletedAtStage();
    }

    public QuestState getQuestState(final UserEntity userEntity, final QuestDefinition questDefinition) {
        if (getQuestStage(userEntity, questDefinition) == 0) {
            return QuestState.NOT_STARTED;
        } else if (getQuestStage(userEntity, questDefinition) == questDefinition.getCompletedAtStage()) {
            return QuestState.COMPLETED;
        }

        return QuestState.STARTED;
    }

    public int getQuestStage(final UserEntity userEntity, final QuestDefinition questDefinition) {
        return metadataEntityFactory.getNumericEntity(userEntity, "QUEST_" + questDefinition.getId() + "_STAGE").getValue();
    }

    public void setQuestStage(final UserEntity userEntity, final QuestDefinition questDefinition, final int newQuestStage) {
        metadataEntityFactory.getNumericEntity(userEntity, "QUEST_" + questDefinition.getId() + "_STAGE").setValue(newQuestStage);
    }
}
