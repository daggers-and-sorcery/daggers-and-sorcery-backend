package com.morethanheroic.swords.monster.service.loader;

import com.google.common.collect.ImmutableList;
import com.morethanheroic.definition.loader.DefinitionLoader;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.monster.service.loader.domain.RawMonsterDefinition;
import com.morethanheroic.swords.monster.service.transformer.MonsterDefinitionTransformer;
import com.morethanheroic.xml.service.loader.NumericXmlDefinitionLoader;
import com.morethanheroic.xml.service.loader.domain.NumericDefinitionLoadingContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class MonsterDefinitionLoader implements DefinitionLoader<MonsterDefinition> {

    private final NumericXmlDefinitionLoader numericXmlDefinitionLoader;
    private final MonsterDefinitionTransformer monsterDefinitionTransformer;

    @Override
    public List<MonsterDefinition> loadDefinitions() {
        return loadRawMonsterDefinitions().stream()
                .map(monsterDefinitionTransformer::transform)
                .collect(collectingAndThen(toList(), ImmutableList::copyOf));
    }

    private List<RawMonsterDefinition> loadRawMonsterDefinitions() {
        return numericXmlDefinitionLoader.loadDefinitions(
                NumericDefinitionLoadingContext.<RawMonsterDefinition>builder()
                        .clazz(RawMonsterDefinition.class)
                        .resourcePath("classpath:data/monster/definition/")
                        .schemaPath("classpath:data/monster/schema.xsd")
                        .build()
        );
    }
}
