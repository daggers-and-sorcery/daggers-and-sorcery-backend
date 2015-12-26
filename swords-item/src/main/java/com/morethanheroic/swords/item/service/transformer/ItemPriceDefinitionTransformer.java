package com.morethanheroic.swords.item.service.transformer;

import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.item.domain.ItemPriceDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawItemPriceDefinition;
import org.springframework.stereotype.Service;

@Service
public class ItemPriceDefinitionTransformer implements DefinitionTransformer<ItemPriceDefinition, RawItemPriceDefinition> {

    public ItemPriceDefinition transform(RawItemPriceDefinition rawItemPriceDefinition) {
        return ItemPriceDefinition.builder()
                .type(rawItemPriceDefinition.getType())
                .amount(rawItemPriceDefinition.getAmount())
                .build();
    }
}
