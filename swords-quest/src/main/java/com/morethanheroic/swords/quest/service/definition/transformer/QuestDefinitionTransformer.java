package com.morethanheroic.swords.quest.service.definition.transformer;

import com.morethanheroic.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.quest.domain.definition.QuestDefinition;
import com.morethanheroic.swords.quest.service.definition.loader.domain.RawQuestDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestDefinitionTransformer implements DefinitionTransformer<QuestDefinition, RawQuestDefinition> {

    private final QuestStateDefinitionTransformer questStateDefinitionTransformer;

    @Override
    public QuestDefinition transform(final RawQuestDefinition rawDefinition) {
        return QuestDefinition.builder()
                .id(rawDefinition.getId())
                .name(rawDefinition.getName())
                .description(rawDefinition.getDescription())
                .states(
                        rawDefinition.getQuestStates().stream()
                                .map(questStateDefinitionTransformer::transform)
                                .collect(Collectors.toList())
                )
                .rewards(rawDefinition.getRewards())
                .build();
    }
}
