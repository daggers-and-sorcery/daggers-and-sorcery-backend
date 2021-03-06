package com.morethanheroic.swords.item.service.definition.transformer.price;

import com.google.common.collect.ImmutableList;
import com.morethanheroic.definition.transformer.DefinitionListTransformer;
import com.morethanheroic.swords.item.domain.price.ItemPriceDefinition;
import com.morethanheroic.swords.item.service.definition.loader.domain.RawItemPriceDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

/**
 * Transform a {@link List} of {@link RawItemPriceDefinition} to a {@link List} of {@link ItemPriceDefinition}.
 */
@Service
@RequiredArgsConstructor
public class ItemPriceDefinitionListTransformer implements DefinitionListTransformer<List<ItemPriceDefinition>, List<RawItemPriceDefinition>> {

    private final ItemPriceDefinitionTransformer itemPriceDefinitionTransformer;

    @Override
    public List<ItemPriceDefinition> transform(List<RawItemPriceDefinition> rawDefinition) {
        if (rawDefinition == null) {
            return Collections.emptyList();
        }

        return rawDefinition.stream().map(itemPriceDefinitionTransformer::transform).collect(collectingAndThen(toList(), ImmutableList::copyOf));
    }
}
