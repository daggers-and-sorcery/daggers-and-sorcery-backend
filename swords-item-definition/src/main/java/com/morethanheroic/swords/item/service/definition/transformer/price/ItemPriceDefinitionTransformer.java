package com.morethanheroic.swords.item.service.definition.transformer.price;

import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.item.domain.price.ItemPriceDefinition;
import com.morethanheroic.swords.item.service.definition.loader.domain.RawItemPriceDefinition;
import org.springframework.stereotype.Service;

/**
 * Transform a {@link RawItemPriceDefinition} to a {@link ItemPriceDefinition}.
 */
@Service
public class ItemPriceDefinitionTransformer implements DefinitionTransformer<ItemPriceDefinition, RawItemPriceDefinition> {

    public ItemPriceDefinition transform(RawItemPriceDefinition rawItemPriceDefinition) {
        return ItemPriceDefinition.builder()
                .type(rawItemPriceDefinition.getType())
                .amount(rawItemPriceDefinition.getAmount())
                .build();
    }
}
