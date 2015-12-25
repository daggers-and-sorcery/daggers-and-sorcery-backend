package com.morethanheroic.swords.item.service.transformer;

import com.morethanheroic.swords.item.domain.ItemRequirementDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawItemRequirementDefinition;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemRequirementDefinitionTransformer {

    public List<ItemRequirementDefinition> transform(List<RawItemRequirementDefinition> rawItemRequirementDefinitionList) {
        if (rawItemRequirementDefinitionList == null) {
            return Collections.emptyList();
        }

        return rawItemRequirementDefinitionList.stream().map(this::transform).collect(Collectors.toList());
    }

    public ItemRequirementDefinition transform(RawItemRequirementDefinition rawItemRequirementDefinition) {
        return ItemRequirementDefinition.builder()
                .amount(rawItemRequirementDefinition.getAmount())
                .requirement(rawItemRequirementDefinition.getRequirement())
                .build();
    }
}
