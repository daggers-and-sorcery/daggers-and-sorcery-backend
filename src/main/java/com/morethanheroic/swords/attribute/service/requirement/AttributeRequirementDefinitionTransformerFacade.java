package com.morethanheroic.swords.attribute.service.requirement;

import com.morethanheroic.swords.attribute.domain.requirement.BasicAttributeRequirementDefinition;
import com.morethanheroic.swords.attribute.service.requirement.transformer.BasicAttributeRequirementDefinitionTransformer;
import com.morethanheroic.swords.item.service.loader.domain.RawBasicAttributeRequirementDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttributeRequirementDefinitionTransformerFacade {

    private BasicAttributeRequirementDefinitionTransformer basicAttributeRequirementDefinitionTransformer;

    @Autowired
    public AttributeRequirementDefinitionTransformerFacade(BasicAttributeRequirementDefinitionTransformer basicAttributeRequirementDefinitionTransformer) {
        this.basicAttributeRequirementDefinitionTransformer = basicAttributeRequirementDefinitionTransformer;
    }

    public BasicAttributeRequirementDefinition transform(RawBasicAttributeRequirementDefinition rawBasicAttributeRequirementDefinition) {
        return basicAttributeRequirementDefinitionTransformer.transform(rawBasicAttributeRequirementDefinition);
    }
}
