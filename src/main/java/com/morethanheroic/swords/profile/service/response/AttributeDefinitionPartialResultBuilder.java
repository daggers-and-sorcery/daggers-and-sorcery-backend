package com.morethanheroic.swords.profile.service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.domain.type.AttributeType;
import com.morethanheroic.swords.attribute.domain.type.GeneralAttributeType;
import com.morethanheroic.swords.attribute.service.cache.SkillAttributeDefinitionCache;
import com.morethanheroic.swords.response.service.PartialResponseBuilder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttributeDefinitionPartialResultBuilder implements PartialResponseBuilder<AttributeDefinitionPartialResponseBuilderConfiguration> {

    @NonNull
    private final SkillAttributeDefinitionCache skillAttributeDefinitionCache;

    @Override
    public AttributeDefinitionPartialResponse build(AttributeDefinitionPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        final Attribute attribute = responseBuilderConfiguration.getAttribute();

        return AttributeDefinitionPartialResponse.builder()
                .id(attribute)
                .name(buildName(attribute))
                .initialValue(attribute.getInitialValue())
                .attributeType(attribute.getAttributeType())
                .generalAttributeType(buildGeneralAttributeType(attribute))
                .build();
    }

    private String buildName(Attribute attribute) {
        if (attribute.getAttributeType() == AttributeType.SKILL) {
            return skillAttributeDefinitionCache.getDefinition((SkillAttribute) attribute).getName();
        } else {
            return attribute.getName();
        }
    }

    //TODO: create a GeneralAttributeDefinitionPartialResult and return that not null here
    private GeneralAttributeType buildGeneralAttributeType(Attribute attribute) {
        if (attribute.getAttributeType() == AttributeType.GENERAL) {
            ((GeneralAttribute) attribute).getGeneralAttributeType();
        }

        return null;
    }
}
