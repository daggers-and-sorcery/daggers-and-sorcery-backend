package com.morethanheroic.swords.item.service.definition.transformer.requirement;

import com.google.common.collect.ImmutableList;
import com.morethanheroic.definition.transformer.DefinitionListTransformer;
import com.morethanheroic.swords.item.domain.requirement.ItemRequirementDefinition;
import com.morethanheroic.swords.item.service.definition.loader.domain.RawItemRequirementDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

/**
 * Transform a {@link List} of {@link RawItemRequirementDefinition} to a {@link List} of {@link ItemRequirementDefinition}.
 */
@Service
@RequiredArgsConstructor
public class ItemRequirementDefinitionListTransformer implements DefinitionListTransformer<List<ItemRequirementDefinition>,
        List<RawItemRequirementDefinition>> {

    private final ItemRequirementDefinitionTransformer itemRequirementDefinitionTransformer;

    @Override
    public List<ItemRequirementDefinition> transform(List<RawItemRequirementDefinition> rawDefinitionList) {
        if (rawDefinitionList == null) {
            return Collections.emptyList();
        }

        return rawDefinitionList.stream().map(itemRequirementDefinitionTransformer::transform).collect(
                collectingAndThen(toList(), ImmutableList::copyOf)
        );
    }
}
