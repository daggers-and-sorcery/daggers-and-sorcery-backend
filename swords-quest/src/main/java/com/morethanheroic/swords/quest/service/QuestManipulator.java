package com.morethanheroic.swords.quest.service;

import com.morethanheroic.swords.quest.domain.definition.QuestDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestManipulator {

    private static final int STARTING_QUEST_STAGE = 1;

    private final QuestStateCalculator questStateCalculator;

    public void startQuest(final UserEntity userEntity, final QuestDefinition questDefinition) {
        questStateCalculator.setQuestState(userEntity, questDefinition, STARTING_QUEST_STAGE);
    }
}
