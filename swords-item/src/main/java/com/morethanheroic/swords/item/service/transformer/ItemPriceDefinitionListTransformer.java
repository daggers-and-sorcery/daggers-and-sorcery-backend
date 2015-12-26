package com.morethanheroic.swords.item.service.transformer;

import com.google.common.collect.ImmutableList;
import com.morethanheroic.swords.definition.transformer.DefinitionListTransformer;
import com.morethanheroic.swords.item.domain.ItemPriceDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawItemPriceDefinition;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ItemPriceDefinitionListTransformer implements DefinitionListTransformer<List<ItemPriceDefinition>, List<RawItemPriceDefinition>> {

    @NonNull
    private final ItemPriceDefinitionTransformer itemPriceDefinitionTransformer;

    @Override
    public List<ItemPriceDefinition> transform(List<RawItemPriceDefinition> rawDefinition) {
        if (rawDefinition == null) {
            return Collections.emptyList();
        }

        return rawDefinition.stream().map(itemPriceDefinitionTransformer::transform).collect(collectingAndThen(toList(), ImmutableList::copyOf));
    }
}
