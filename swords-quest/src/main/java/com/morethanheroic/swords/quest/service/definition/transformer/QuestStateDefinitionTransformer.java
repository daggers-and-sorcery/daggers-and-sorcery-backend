package com.morethanheroic.swords.quest.service.definition.transformer;

import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.quest.domain.definition.QuestStateDefinition;
import com.morethanheroic.swords.quest.service.definition.loader.domain.RawQuestStateDefinition;
import org.springframework.stereotype.Service;

@Service
public class QuestStateDefinitionTransformer implements DefinitionTransformer<QuestStateDefinition, RawQuestStateDefinition> {

    @Override
    public QuestStateDefinition transform(RawQuestStateDefinition rawDefinition) {
        return QuestStateDefinition.builder()
                .description(rawDefinition.getDescription())
                .build();
    }
}
