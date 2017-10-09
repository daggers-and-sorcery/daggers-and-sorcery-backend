package com.morethanheroic.swords.item.service.definition.transformer.modifier;

import com.google.common.collect.ImmutableList;
import com.morethanheroic.definition.transformer.DefinitionListTransformer;
import com.morethanheroic.swords.item.domain.modifier.ItemModifierDefinition;
import com.morethanheroic.swords.item.service.definition.loader.domain.RawItemModifierDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

/**
 * Transform a {@link List} of {@link RawItemModifierDefinition} to a {@link List} of {@link ItemModifierDefinition}.
 */
@Service
@RequiredArgsConstructor
public class ItemModifierDefinitionListTransformer implements DefinitionListTransformer<List<ItemModifierDefinition>,
        List<RawItemModifierDefinition>> {

    private final ItemModifierDefinitionTransformer itemModifierDefinitionTransformer;

    @Override
    public List<ItemModifierDefinition> transform(List<RawItemModifierDefinition> rawItemModifierDefinitionList) {
        if (rawItemModifierDefinitionList == null) {
            return Collections.emptyList();
        }

        return rawItemModifierDefinitionList.stream().map(itemModifierDefinitionTransformer::transform).collect(
                collectingAndThen(toList(), ImmutableList::copyOf)
        );
    }
}
