package com.morethanheroic.swords.shop.view.response.service.buy;

import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.view.response.service.ItemModifierPartialResponseBuilder;
import com.morethanheroic.swords.item.view.response.service.ItemRequirementPartialResponseBuilder;
import com.morethanheroic.swords.item.view.response.service.domain.configuration.ItemModifierPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.item.view.response.service.domain.configuration.ItemRequirementPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.shop.domain.ShopItem;
import com.morethanheroic.swords.shop.view.response.domain.buy.ShopBuyItemPartialResponse;
import com.morethanheroic.swords.shop.view.response.domain.buy.configuration.ShopBuyListItemPartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopBuyListItemPartialResponseBuilder implements PartialResponseCollectionBuilder<ShopBuyListItemPartialResponseBuilderConfiguration> {

    private final ItemModifierPartialResponseBuilder itemModifierPartialResponseBuilder;
    private final ItemRequirementPartialResponseBuilder itemRequirementPartialResponseBuilder;

    @Override
    public List<ShopBuyItemPartialResponse> build(ShopBuyListItemPartialResponseBuilderConfiguration buyListItemPartialResponseBuilderConfiguration) {
        final List<ShopBuyItemPartialResponse> result = new ArrayList<>();

        for (ShopItem shopItem : buyListItemPartialResponseBuilderConfiguration.getShopItems()) {
            final ItemDefinition item = shopItem.getItem();

            result.add(
                    ShopBuyItemPartialResponse.builder()
                            .id(shopItem.getItem().getId())
                            .price(shopItem.getBuyPrice())
                            .amount(shopItem.getAmount())
                            .name(item.getName())
                            .description(item.getDescription())
                            .flavour(item.getFlavour())
                            .equipment(item.isEquipment())
                            .type(item.getType().getName())
                            .subtype(item.getSubtype().getName())
                            .usable(item.isUsable())
                            .weight(item.getWeight())
                            .modifiers(
                                    item.getModifiers().stream()
                                            .map(itemModifierDefinition -> itemModifierPartialResponseBuilder.build(
                                                    ItemModifierPartialResponseBuilderConfiguration.builder()
                                                            .amount(itemModifierDefinition.getAmount())
                                                            .d2(itemModifierDefinition.getD2())
                                                            .d4(itemModifierDefinition.getD4())
                                                            .d6(itemModifierDefinition.getD6())
                                                            .d8(itemModifierDefinition.getD8())
                                                            .d10(itemModifierDefinition.getD10())
                                                            .itemModifier(itemModifierDefinition.getModifier())
                                                            .build()
                                                    )
                                            ).collect(Collectors.toList())
                            )
                            .requirements(
                                    item.getRequirements().stream()
                                            .map(itemRequirementDefinition -> itemRequirementPartialResponseBuilder.build(
                                                    ItemRequirementPartialResponseBuilderConfiguration.builder()
                                                            .amount(itemRequirementDefinition.getAmount())
                                                            .itemRequirement(itemRequirementDefinition.getRequirement())
                                                            .build()
                                                    )
                                            ).collect(Collectors.toList())
                            )
                            .build()
            );
        }

        return result;
    }
}
