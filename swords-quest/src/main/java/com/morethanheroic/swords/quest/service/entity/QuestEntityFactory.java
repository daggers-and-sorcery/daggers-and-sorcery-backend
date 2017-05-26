package com.morethanheroic.swords.quest.service.entity;

import com.morethanheroic.entity.service.factory.EntityFactory;
import com.morethanheroic.swords.quest.domain.QuestEntity;
import com.morethanheroic.swords.quest.domain.definition.QuestDefinition;
import com.morethanheroic.swords.quest.service.QuestStateCalculator;
import com.morethanheroic.swords.quest.service.definition.cache.QuestDefinitionCache;
import com.morethanheroic.swords.quest.service.entity.domain.QuestEntityFactoryContext;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestEntityFactory implements EntityFactory<QuestEntity, QuestEntityFactoryContext> {

    private final QuestStateCalculator questStateCalculator;
    private final QuestDefinitionCache questDefinitionCache;

    @Override
    public QuestEntity getEntity(final QuestEntityFactoryContext questEntityFactoryContext) {
        final UserEntity userEntity = questEntityFactoryContext.getUserEntity();
        final QuestDefinition questDefinition = questEntityFactoryContext.getQuestDefinition();

        return QuestEntity.builder()
                .questDefinition(questDefinition)
                .stage(questStateCalculator.getQuestStage(userEntity, questDefinition))
                .state(questStateCalculator.getQuestState(userEntity, questDefinition))
                .build();
    }
}
