package com.morethanheroic.swords.item.service.transformer;

import com.morethanheroic.swords.attribute.domain.requirement.BasicAttributeRequirementDefinition;
import com.morethanheroic.swords.attribute.domain.requirement.CombatAttributeRequirementDefinition;
import com.morethanheroic.swords.attribute.domain.requirement.GeneralAttributeRequirementDefinition;
import com.morethanheroic.swords.attribute.domain.requirement.SkillAttributeRequirementDefinition;
import com.morethanheroic.swords.attribute.service.requirement.AttributeRequirementDefinitionTransformerFacade;
import com.morethanheroic.swords.item.service.loader.domain.RawBasicAttributeRequirementDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawCombatAttributeRequirementDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawGeneralAttributeRequirementDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawSkillAttributeRequirementDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Deprecated
public class ItemDefinitionRequirementListTransformer {

    @Autowired
    private AttributeRequirementDefinitionTransformerFacade attributeRequirementDefinitionTransformerFacade;

    public List<GeneralAttributeRequirementDefinition> transformGeneralRequirement(List<RawGeneralAttributeRequirementDefinition> rawGeneralAttributeRequirementDefinitionList) {
        if (rawGeneralAttributeRequirementDefinitionList == null) {
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(rawGeneralAttributeRequirementDefinitionList.stream().map(attributeRequirementDefinitionTransformerFacade::transform).collect(Collectors.toList()));
    }

    public List<SkillAttributeRequirementDefinition> transformSkillRequirement(List<RawSkillAttributeRequirementDefinition> rawSkillAttributeRequirementDefinitionList) {
        if (rawSkillAttributeRequirementDefinitionList == null) {
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(rawSkillAttributeRequirementDefinitionList.stream().map(attributeRequirementDefinitionTransformerFacade::transform).collect(Collectors.toList()));
    }

    public List<CombatAttributeRequirementDefinition> transformCombatRequirement(List<RawCombatAttributeRequirementDefinition> rawCombatAttributeRequirementDefinitionList) {
        if (rawCombatAttributeRequirementDefinitionList == null) {
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(rawCombatAttributeRequirementDefinitionList.stream().map(attributeRequirementDefinitionTransformerFacade::transform).collect(Collectors.toList()));
    }

    public List<BasicAttributeRequirementDefinition> transformBasicRequirement(List<RawBasicAttributeRequirementDefinition> rawBasicAttributeRequirementDefinitionList) {
        if (rawBasicAttributeRequirementDefinitionList == null) {
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(rawBasicAttributeRequirementDefinitionList.stream().map(attributeRequirementDefinitionTransformerFacade::transform).collect(Collectors.toList()));
    }
}
