package com.morethanheroic.swords.market.view.controller.sell;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.market.domain.SellingResult;
import com.morethanheroic.swords.market.service.SellingService;
import com.morethanheroic.swords.market.view.request.domain.SellItemData;
import com.morethanheroic.swords.market.view.response.service.domain.sell.SellItemResponseBuilderConfiguration;
import com.morethanheroic.swords.market.view.response.service.sell.SellItemResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class SellItemToMarketController {

    private final SellingService sellingService;
    private final ItemDefinitionCache itemDefinitionCache;
    private final ResponseFactory responseFactory;
    private final SellItemResponseBuilder sellItemResponseBuilder;

    @PostMapping("/market/sell")
    public Response sellItem(final UserEntity userEntity, @RequestBody @Valid final SellItemData sellItemData) {
        final SellingResult sellingResult = sellingService.sellToMarket(userEntity, itemDefinitionCache.getDefinition(sellItemData.getItem()), calculatePrice(sellItemData), sellItemData.getAmount());

        return sellItemResponseBuilder.build(
                SellItemResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .sellingResult(sellingResult)
                        .build()
        );
    }

    //TODO: Move this to somewhere else. Maybe create a calculator service for money calculation? How?
    private int calculatePrice(final SellItemData sellItemData) {
        return sellItemData.getPriceBronze() + sellItemData.getPriceSilver() * 100 + sellItemData.getPriceGold() * 100 * 100;
    }
}
