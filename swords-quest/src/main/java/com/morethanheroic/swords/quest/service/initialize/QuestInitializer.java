package com.morethanheroic.swords.quest.service.initialize;

import com.morethanheroic.swords.quest.domain.definition.QuestDefinition;
import com.morethanheroic.swords.quest.service.QuestManipulator;
import com.morethanheroic.swords.quest.service.initialize.validator.QuestInitializationValidator;
import com.morethanheroic.swords.quest.service.initialize.validator.domain.ValidationContext;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class QuestInitializer {

    private final QuestManipulator questManipulator;
    private final Map<Integer, QuestInitializationValidator> questInitializationValidators;

    public QuestInitializer(final QuestManipulator questManipulator, final List<QuestInitializationValidator> questInitializationValidatorList) {
        this.questManipulator = questManipulator;

        questInitializationValidators = questInitializationValidatorList.stream()
                .collect(Collectors.toMap((quest) -> quest.supportedQuest().getId(), Function.identity()));
    }

    public void startQuest(final UserEntity userEntity, final QuestDefinition questDefinition) {
        if (validateQuest(userEntity, questDefinition)) {
            questManipulator.startQuest(userEntity, questDefinition);
        }
    }

    private boolean validateQuest(final UserEntity userEntity, final QuestDefinition questDefinition) {
        return !questInitializationValidators.containsKey(questDefinition.getId()) || questInitializationValidators.get(questDefinition.getId()).isValidStart(
                ValidationContext.builder()
                        .userEntity(userEntity)
                        .build()
        );
    }
}
