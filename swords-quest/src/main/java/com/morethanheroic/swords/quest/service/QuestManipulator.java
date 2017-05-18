package com.morethanheroic.swords.quest.service;

import org.springframework.stereotype.Service;

import com.morethanheroic.swords.metadata.service.MetadataEntityFactory;
import com.morethanheroic.swords.quest.domain.definition.QuestDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestManipulator {

    private static final int STARTING_QUEST_STAGE = 1;

    private final MetadataEntityFactory metadataEntityFactory;

    public boolean startedQuest(final UserEntity userEntity, final QuestDefinition questDefinition) {
        return metadataEntityFactory.getNumericEntity(userEntity, "QUEST_" + questDefinition.getId() + "_STAGE").getValue() > 0;
    }

    public void startQuest(final UserEntity userEntity, final QuestDefinition questDefinition) {
        metadataEntityFactory.getNumericEntity(userEntity, "QUEST_" + questDefinition.getId() + "_STAGE").setValue(STARTING_QUEST_STAGE);
    }
}
