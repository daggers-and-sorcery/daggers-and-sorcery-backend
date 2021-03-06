package com.morethanheroic.swords.attribute.service.loader;

import com.google.common.collect.ImmutableList;
import com.morethanheroic.definition.loader.DefinitionLoader;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.domain.SkillAttributeDefinition;
import com.morethanheroic.swords.attribute.service.loader.entity.RawSkillAttributeDefinition;
import com.morethanheroic.swords.attribute.service.transformer.SkillAttributeDefinitionTransformer;
import com.morethanheroic.xml.service.loader.EnumXmlDefinitionLoader;
import com.morethanheroic.xml.service.loader.domain.EnumDefinitionLoadingContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class SkillAttributeDefinitionLoader implements DefinitionLoader<SkillAttributeDefinition> {

    private final EnumXmlDefinitionLoader enumXmlDefinitionLoader;
    private final SkillAttributeDefinitionTransformer skillAttributeDefinitionTransformer;

    @Override
    public List<SkillAttributeDefinition> loadDefinitions() {
        return loadRawSkillAttributeDefinitions().stream()
                .map(skillAttributeDefinitionTransformer::transform)
                .collect(
                        collectingAndThen(toList(), ImmutableList::copyOf)
                );
    }

    private List<RawSkillAttributeDefinition> loadRawSkillAttributeDefinitions() {
        return enumXmlDefinitionLoader.loadDefinitions(
                EnumDefinitionLoadingContext.<RawSkillAttributeDefinition>builder()
                        .clazz(RawSkillAttributeDefinition.class)
                        .resourcePath("classpath:data/attribute/skill/definition/")
                        .schemaPath("classpath:data/attribute/skill/schema.xsd")
                        .target(SkillAttribute.class)
                        .build()
        );
    }
}
