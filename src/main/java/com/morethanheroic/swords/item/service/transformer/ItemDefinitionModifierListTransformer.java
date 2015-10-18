package com.morethanheroic.swords.item.service.transformer;

import com.morethanheroic.swords.attribute.domain.modifier.BasicAttributeModifierDefinition;
import com.morethanheroic.swords.attribute.domain.modifier.CombatAttributeModifierDefinition;
import com.morethanheroic.swords.attribute.domain.modifier.GeneralAttributeModifierDefinition;
import com.morethanheroic.swords.attribute.domain.modifier.SkillAttributeModifierDefinition;
import com.morethanheroic.swords.attribute.service.modifier.AttributeModifierDefinitionTransformerFacade;
import com.morethanheroic.swords.item.service.loader.domain.RawBasicAttributeModifierDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawCombatAttributeModifierDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawGeneralAttributeModifierDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawSkillAttributeModifierDefinition;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemDefinitionModifierListTransformer {

    private final AttributeModifierDefinitionTransformerFacade attributeModifierDefinitionTransformerFacade;

    public ItemDefinitionModifierListTransformer(AttributeModifierDefinitionTransformerFacade attributeModifierDefinitionTransformerFacade) {
        this.attributeModifierDefinitionTransformerFacade = attributeModifierDefinitionTransformerFacade;
    }

    public List<BasicAttributeModifierDefinition> transformBasicModifier(List<RawBasicAttributeModifierDefinition> rawAttributeModifierDefinitionList) {
        if (rawAttributeModifierDefinitionList == null) {
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(rawAttributeModifierDefinitionList.stream().map(attributeModifierDefinitionTransformerFacade::transform).collect(Collectors.toList()));
    }

    public List<CombatAttributeModifierDefinition> transformCombatModifier(List<RawCombatAttributeModifierDefinition> rawAttributeModifierDefinitionList) {
        if (rawAttributeModifierDefinitionList == null) {
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(rawAttributeModifierDefinitionList.stream().map(attributeModifierDefinitionTransformerFacade::transform).collect(Collectors.toList()));
    }

    public List<GeneralAttributeModifierDefinition> transformGeneralModifier(List<RawGeneralAttributeModifierDefinition> rawAttributeModifierDefinitionList) {
        if (rawAttributeModifierDefinitionList == null) {
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(rawAttributeModifierDefinitionList.stream().map(attributeModifierDefinitionTransformerFacade::transform).collect(Collectors.toList()));
    }

    public List<SkillAttributeModifierDefinition> transformSkillModifier(List<RawSkillAttributeModifierDefinition> rawAttributeModifierDefinitionList) {
        if (rawAttributeModifierDefinitionList == null) {
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(rawAttributeModifierDefinitionList.stream().map(attributeModifierDefinitionTransformerFacade::transform).collect(Collectors.toList()));
    }
}
