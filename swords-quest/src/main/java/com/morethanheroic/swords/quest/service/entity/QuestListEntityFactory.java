package com.morethanheroic.swords.quest.service.entity;

import com.morethanheroic.entity.service.factory.EntityListFactory;
import com.morethanheroic.swords.quest.domain.QuestEntity;
import com.morethanheroic.swords.quest.service.definition.cache.QuestDefinitionCache;
import com.morethanheroic.swords.quest.service.entity.domain.QuestEntityFactoryContext;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestListEntityFactory implements EntityListFactory<QuestEntity, UserEntity> {

    private final QuestDefinitionCache questDefinitionCache;
    private final QuestEntityFactory questEntityFactory;

    @Override
    public List<QuestEntity> getEntity(final UserEntity userEntity) {
        return questDefinitionCache.getDefinitions().stream()
                .map(questDefinition ->
                        questEntityFactory.getEntity(
                                QuestEntityFactoryContext.builder()
                                        .userEntity(userEntity)
                                        .questDefinition(questDefinition)
                                        .build()
                        )
                )
                .collect(Collectors.toList());
    }
}
