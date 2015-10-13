package com.morethanheroic.swords.attribute.service.modifier.transformer;

import com.morethanheroic.swords.attribute.domain.modifier.BasicAttributeModifierDefinition;
import com.morethanheroic.swords.attribute.domain.modifier.GeneralAttributeModifierDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawBasicAttributeModifierDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawGeneralAttributeModifierDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttributeModifierDefinitionTransformerFacade {

    private final BasicAttributeModifierDefinitionTransformer basicAttributeModifierDefinitionTransformer;
    private final GeneralAttributeModifierDefinitionTransformer generalAttributeModifierDefinitionTransformer;

    @Autowired
    public AttributeModifierDefinitionTransformerFacade(BasicAttributeModifierDefinitionTransformer basicAttributeModifierDefinitionTransformer, GeneralAttributeModifierDefinitionTransformer generalAttributeModifierDefinitionTransformer) {
        this.basicAttributeModifierDefinitionTransformer = basicAttributeModifierDefinitionTransformer;
        this.generalAttributeModifierDefinitionTransformer = generalAttributeModifierDefinitionTransformer;
    }

    public BasicAttributeModifierDefinition transform(RawBasicAttributeModifierDefinition rawBasicAttributeModifierDefinition) {
        return basicAttributeModifierDefinitionTransformer.transform(rawBasicAttributeModifierDefinition);
    }

    public GeneralAttributeModifierDefinition transform(RawGeneralAttributeModifierDefinition rawGeneralAttributeModifierDefinition) {
        return generalAttributeModifierDefinitionTransformer.transform(rawGeneralAttributeModifierDefinition);
    }
}
