package com.morethanheroic.swords.item.service.transformer;

import com.google.common.collect.ImmutableList;
import com.morethanheroic.swords.item.domain.ItemRequirementDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawItemRequirementDefinition;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
public class ItemRequirementDefinitionTransformer {

    public List<ItemRequirementDefinition> transform(List<RawItemRequirementDefinition> rawItemRequirementDefinitionList) {
        if (rawItemRequirementDefinitionList == null) {
            return Collections.emptyList();
        }

        return rawItemRequirementDefinitionList.stream().map(this::transform).collect(collectingAndThen(toList(), ImmutableList::copyOf));
    }

    public ItemRequirementDefinition transform(RawItemRequirementDefinition rawItemRequirementDefinition) {
        return ItemRequirementDefinition.builder()
                .amount(rawItemRequirementDefinition.getAmount())
                .requirement(rawItemRequirementDefinition.getRequirement())
                .build();
    }
}
