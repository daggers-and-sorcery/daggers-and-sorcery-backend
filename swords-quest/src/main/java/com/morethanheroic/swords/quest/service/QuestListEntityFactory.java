package com.morethanheroic.swords.quest.service;

import com.morethanheroic.entity.service.factory.EntityListFactory;
import com.morethanheroic.swords.quest.domain.QuestEntity;
import com.morethanheroic.swords.quest.service.definition.cache.QuestDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestListEntityFactory implements EntityListFactory<QuestEntity, UserEntity> {

    private final QuestStateCalculator questStateCalculator;
    private final QuestDefinitionCache questDefinitionCache;

    @Override
    public List<QuestEntity> getEntity(final UserEntity userEntity) {
        return questDefinitionCache.getDefinitions().stream()
                .map(questDefinition ->
                        QuestEntity.builder()
                                .questDefinition(questDefinition)
                                .stage(questStateCalculator.getQuestStage(userEntity, questDefinition))
                                .state(questStateCalculator.getQuestState(userEntity, questDefinition))
                                .build()
                )
                .collect(Collectors.toList());
    }
}
