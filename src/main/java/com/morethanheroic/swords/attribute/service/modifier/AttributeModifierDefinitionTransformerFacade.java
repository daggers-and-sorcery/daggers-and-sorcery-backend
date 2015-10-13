package com.morethanheroic.swords.attribute.service.modifier;

import com.morethanheroic.swords.attribute.domain.modifier.BasicAttributeModifierDefinition;
import com.morethanheroic.swords.attribute.domain.modifier.CombatAttributeModifierDefinition;
import com.morethanheroic.swords.attribute.domain.modifier.GeneralAttributeModifierDefinition;
import com.morethanheroic.swords.attribute.domain.modifier.SkillAttributeModifierDefinition;
import com.morethanheroic.swords.attribute.service.modifier.transformer.BasicAttributeModifierDefinitionTransformer;
import com.morethanheroic.swords.attribute.service.modifier.transformer.CombatAttributeModifierDefinitionTransformer;
import com.morethanheroic.swords.attribute.service.modifier.transformer.GeneralAttributeModifierDefinitionTransformer;
import com.morethanheroic.swords.attribute.service.modifier.transformer.SkillAttributeModifierDefinitionTransformer;
import com.morethanheroic.swords.item.service.loader.domain.RawBasicAttributeModifierDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawCombatAttributeModifierDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawGeneralAttributeModifierDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawSkillAttributeModifierDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttributeModifierDefinitionTransformerFacade {

    private final BasicAttributeModifierDefinitionTransformer basicAttributeModifierDefinitionTransformer;
    private final GeneralAttributeModifierDefinitionTransformer generalAttributeModifierDefinitionTransformer;
    private final SkillAttributeModifierDefinitionTransformer skillAttributeModifierDefinitionTransformer;
    private final CombatAttributeModifierDefinitionTransformer combatAttributeModifierDefinitionTransformer;

    @Autowired
    public AttributeModifierDefinitionTransformerFacade(BasicAttributeModifierDefinitionTransformer basicAttributeModifierDefinitionTransformer, GeneralAttributeModifierDefinitionTransformer generalAttributeModifierDefinitionTransformer, SkillAttributeModifierDefinitionTransformer skillAttributeModifierDefinitionTransformer, CombatAttributeModifierDefinitionTransformer combatAttributeModifierDefinitionTransformer) {
        this.basicAttributeModifierDefinitionTransformer = basicAttributeModifierDefinitionTransformer;
        this.generalAttributeModifierDefinitionTransformer = generalAttributeModifierDefinitionTransformer;
        this.skillAttributeModifierDefinitionTransformer = skillAttributeModifierDefinitionTransformer;
        this.combatAttributeModifierDefinitionTransformer = combatAttributeModifierDefinitionTransformer;
    }

    public BasicAttributeModifierDefinition transform(RawBasicAttributeModifierDefinition rawBasicAttributeModifierDefinition) {
        return basicAttributeModifierDefinitionTransformer.transform(rawBasicAttributeModifierDefinition);
    }

    public GeneralAttributeModifierDefinition transform(RawGeneralAttributeModifierDefinition rawGeneralAttributeModifierDefinition) {
        return generalAttributeModifierDefinitionTransformer.transform(rawGeneralAttributeModifierDefinition);
    }

    public SkillAttributeModifierDefinition transform(RawSkillAttributeModifierDefinition rawSkillAttributeModifierDefinition) {
        return skillAttributeModifierDefinitionTransformer.transform(rawSkillAttributeModifierDefinition);
    }

    public CombatAttributeModifierDefinition transform(RawCombatAttributeModifierDefinition rawCombatAttributeModifierDefinition) {
        return combatAttributeModifierDefinitionTransformer.transform(rawCombatAttributeModifierDefinition);
    }
}
