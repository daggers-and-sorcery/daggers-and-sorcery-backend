package com.morethanheroic.swords.quest.service.definition.loader;

import com.google.common.collect.ImmutableList;
import com.morethanheroic.definition.loader.DefinitionLoader;
import com.morethanheroic.swords.definition.service.loader.NumericXmlDefinitionLoader;
import com.morethanheroic.swords.definition.service.loader.domain.NumericDefinitionLoadingContext;
import com.morethanheroic.swords.quest.domain.definition.QuestDefinition;
import com.morethanheroic.swords.quest.service.definition.loader.domain.RawQuestDefinition;
import com.morethanheroic.swords.quest.service.definition.transformer.QuestDefinitionTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class QuestDefinitionLoader implements DefinitionLoader<QuestDefinition> {

    private static final String QUEST_DEFINITION_LOCATION = "classpath:data/quest/definition/";
    private static final String QUEST_SCHEMA_LOCATION = "classpath:data/quest/schema.xsd";

    private final NumericXmlDefinitionLoader numericXmlDefinitionLoader;
    private final QuestDefinitionTransformer questDefinitionTransformer;

    @Override
    public List<QuestDefinition> loadDefinitions() {
        return loadRawQuestDefinitions().stream()
                .map(questDefinitionTransformer::transform)
                .collect(collectingAndThen(toList(), ImmutableList::copyOf));
    }

    private List<RawQuestDefinition> loadRawQuestDefinitions() {
        return numericXmlDefinitionLoader.loadDefinitions(
                NumericDefinitionLoadingContext.<RawQuestDefinition>builder()
                        .clazz(RawQuestDefinition.class)
                        .resourcePath(QUEST_DEFINITION_LOCATION)
                        .schemaPath(QUEST_SCHEMA_LOCATION)
                        .build()
        );
    }
}
