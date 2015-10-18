package com.morethanheroic.swords.attribute.service.requirement;

import com.morethanheroic.swords.attribute.domain.requirement.BasicAttributeRequirementDefinition;
import com.morethanheroic.swords.attribute.domain.requirement.CombatAttributeRequirementDefinition;
import com.morethanheroic.swords.attribute.domain.requirement.GeneralAttributeRequirementDefinition;
import com.morethanheroic.swords.attribute.domain.requirement.SkillAttributeRequirementDefinition;
import com.morethanheroic.swords.attribute.service.requirement.transformer.BasicAttributeRequirementDefinitionTransformer;
import com.morethanheroic.swords.attribute.service.requirement.transformer.CombatAttributeRequirementDefinitionTransformer;
import com.morethanheroic.swords.attribute.service.requirement.transformer.GeneralAttributeRequirementDefinitionTransformer;
import com.morethanheroic.swords.attribute.service.requirement.transformer.SkillAttributeRequirementDefinitionTransformer;
import com.morethanheroic.swords.item.service.loader.domain.RawBasicAttributeRequirementDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawCombatAttributeRequirementDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawGeneralAttributeRequirementDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawSkillAttributeRequirementDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttributeRequirementDefinitionTransformerFacade {

    private final BasicAttributeRequirementDefinitionTransformer basicAttributeRequirementDefinitionTransformer;
    private final CombatAttributeRequirementDefinitionTransformer combatAttributeRequirementDefinitionTransformer;
    private final SkillAttributeRequirementDefinitionTransformer skillAttributeRequirementDefinitionTransformer;
    private final GeneralAttributeRequirementDefinitionTransformer generalAttributeRequirementDefinitionTransformer;

    @Autowired
    public AttributeRequirementDefinitionTransformerFacade(BasicAttributeRequirementDefinitionTransformer basicAttributeRequirementDefinitionTransformer, CombatAttributeRequirementDefinitionTransformer combatAttributeRequirementDefinitionTransformer, SkillAttributeRequirementDefinitionTransformer skillAttributeRequirementDefinitionTransformer, GeneralAttributeRequirementDefinitionTransformer generalAttributeRequirementDefinitionTransformer) {
        this.basicAttributeRequirementDefinitionTransformer = basicAttributeRequirementDefinitionTransformer;
        this.combatAttributeRequirementDefinitionTransformer = combatAttributeRequirementDefinitionTransformer;
        this.skillAttributeRequirementDefinitionTransformer = skillAttributeRequirementDefinitionTransformer;
        this.generalAttributeRequirementDefinitionTransformer = generalAttributeRequirementDefinitionTransformer;
    }

    public BasicAttributeRequirementDefinition transform(RawBasicAttributeRequirementDefinition rawBasicAttributeRequirementDefinition) {
        return basicAttributeRequirementDefinitionTransformer.transform(rawBasicAttributeRequirementDefinition);
    }

    public CombatAttributeRequirementDefinition transform(RawCombatAttributeRequirementDefinition rawCombatAttributeRequirementDefinition) {
        return combatAttributeRequirementDefinitionTransformer.transform(rawCombatAttributeRequirementDefinition);
    }

    public SkillAttributeRequirementDefinition transform(RawSkillAttributeRequirementDefinition rawSkillAttributeRequirementDefinition) {
        return skillAttributeRequirementDefinitionTransformer.transform(rawSkillAttributeRequirementDefinition);
    }

    public GeneralAttributeRequirementDefinition transform(RawGeneralAttributeRequirementDefinition rawGeneralAttributeRequirementDefinition) {
        return generalAttributeRequirementDefinitionTransformer.transform(rawGeneralAttributeRequirementDefinition);
    }
}
