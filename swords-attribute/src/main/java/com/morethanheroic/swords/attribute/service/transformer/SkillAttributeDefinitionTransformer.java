package com.morethanheroic.swords.attribute.service.transformer;

import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.domain.SkillAttributeDefinition;
import com.morethanheroic.swords.attribute.service.loader.entity.RawSkillAttributeDefinition;
import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class SkillAttributeDefinitionTransformer implements DefinitionTransformer<SkillAttributeDefinition, RawSkillAttributeDefinition> {

    @Override
    public SkillAttributeDefinition transform(RawSkillAttributeDefinition rawDefinition) {
        return SkillAttributeDefinition.builder()
                .name(rawDefinition.getName())
                .skillAttribute(buildSkillAttributeFromName(rawDefinition.getName()))
                .incrementedAttribute(rawDefinition.getIncrementedAttribute())
                .hasPage(rawDefinition.isHasPage())
                .build();
    }

    private SkillAttribute buildSkillAttributeFromName(String name) {
        return SkillAttribute.valueOf(name.replace(' ', '_').toUpperCase(Locale.ENGLISH));
    }
}
