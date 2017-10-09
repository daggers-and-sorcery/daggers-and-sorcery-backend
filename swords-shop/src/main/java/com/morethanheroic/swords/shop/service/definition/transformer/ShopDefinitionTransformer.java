package com.morethanheroic.swords.shop.service.definition.transformer;

import com.morethanheroic.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.item.service.definition.cache.ItemDefinitionCache;
import com.morethanheroic.swords.shop.domain.AvailableFeatures;
import com.morethanheroic.swords.shop.domain.AvailableItem;
import com.morethanheroic.swords.shop.domain.ShopDefinition;
import com.morethanheroic.swords.shop.service.definition.loader.domain.RawAvailableFeatures;
import com.morethanheroic.swords.shop.service.definition.loader.domain.RawAvailableItem;
import com.morethanheroic.swords.shop.service.definition.loader.domain.RawShopDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopDefinitionTransformer implements DefinitionTransformer<ShopDefinition, RawShopDefinition> {

    private final ItemDefinitionCache itemDefinitionCache;

    public ShopDefinition transform(final RawShopDefinition rawShopDefinition) {
        return ShopDefinition.builder()
                .id(rawShopDefinition.getId())
                .name(rawShopDefinition.getName())
                .availableFeatures(transform(rawShopDefinition.getAvailableFeatures()))
                .availableItems(transform(rawShopDefinition.getAvailableItems()))
                .build();
    }

    private AvailableFeatures transform(final RawAvailableFeatures rawAvailableFeatures) {
        if (rawAvailableFeatures == null) {
            return AvailableFeatures.builder()
                    .buying(true)
                    .selling(true)
                    .build();
        }

        return AvailableFeatures.builder()
                .buying(rawAvailableFeatures.isBuying())
                .selling(rawAvailableFeatures.isSelling())
                .build();
    }

    private List<AvailableItem> transform(final List<RawAvailableItem> rawAvailableItems) {
        if (rawAvailableItems == null) {
            return Collections.emptyList();
        }

        return rawAvailableItems.stream()
                .map(rawAvailableItem ->
                        AvailableItem.builder()
                                .item(itemDefinitionCache.getDefinition(rawAvailableItem.getItemId()))
                                .build()
                )
                .collect(Collectors.toList());
    }
}
