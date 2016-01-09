package com.morethanheroic.swords.attribute.service.loader;

import com.google.common.collect.ImmutableList;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.domain.SkillAttributeDefinition;
import com.morethanheroic.swords.attribute.service.loader.entity.RawSkillAttributeDefinition;
import com.morethanheroic.swords.attribute.service.transformer.SkillAttributeDefinitionTransformer;
import com.morethanheroic.swords.definition.loader.DefinitionLoader;
import com.morethanheroic.swords.definition.service.loader.EnumXmlDefinitionLoader;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SkillAttributeDefinitionLoader implements DefinitionLoader<SkillAttributeDefinition> {

    @NonNull
    private final EnumXmlDefinitionLoader enumXmlDefinitionLoader;

    @NonNull
    private final SkillAttributeDefinitionTransformer skillAttributeDefinitionTransformer;

    @Override
    public List<SkillAttributeDefinition> loadDefinitions() throws IOException {
        return loadRawSkillAttributeEntities().stream().map(skillAttributeDefinitionTransformer::transform).collect(
                collectingAndThen(toList(), ImmutableList::copyOf)
        );
    }

    @SuppressWarnings("unchecked")
    public List<RawSkillAttributeDefinition> loadRawSkillAttributeEntities() throws IOException {
        return enumXmlDefinitionLoader.loadDefinitions(RawSkillAttributeDefinition.class, "classpath:data/attribute/skill/definition/", "classpath:data/attribute/skill/schema.xsd",
                SkillAttribute.class);
    }
}
