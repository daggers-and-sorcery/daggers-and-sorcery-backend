package com.morethanheroic.swords.quest.service.definition.transformer;

import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.quest.domain.definition.QuestDefinition;
import com.morethanheroic.swords.quest.service.definition.loader.domain.RawQuestDefinition;
import org.springframework.stereotype.Service;

@Service
public class QuestDefinitionTransformer implements DefinitionTransformer<QuestDefinition, RawQuestDefinition> {

    @Override
    public QuestDefinition transform(RawQuestDefinition rawDefinition) {
        return QuestDefinition.builder()
                .id(rawDefinition.getId())
                .name(rawDefinition.getName())
                .definition(rawDefinition.getDescription())
                .build();
    }
}
