package com.morethanheroic.swords.market.view.controller.sell;

import javax.validation.Valid;

import com.morethanheroic.swords.market.service.SellerService;
import com.morethanheroic.swords.response.service.ResponseFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.market.service.MarketService;
import com.morethanheroic.swords.market.view.request.domain.SellItemData;
import com.morethanheroic.swords.user.domain.UserEntity;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SellItemToMarketController {

    private final SellerService sellerService;
    private final ItemDefinitionCache itemDefinitionCache;
    private final ResponseFactory responseFactory;

    @PostMapping("/market/sell")
    public Response sellItem(final UserEntity userEntity, @RequestBody @Valid final SellItemData sellItemData) {

        sellerService.sellToMarket(userEntity, itemDefinitionCache.getDefinition(sellItemData.getItem()), calculatePrice(sellItemData), sellItemData.getAmount());

        return responseFactory.successfulResponse(userEntity);
    }

    //TODO: Move this to somewhere else. Maybe create a calculator service for money calculation? How?
    private int calculatePrice(final SellItemData sellItemData) {
        return sellItemData.getPriceBronze() + sellItemData.getPriceSilver() * 100 + sellItemData.getPriceGold() * 100 * 100;
    }
}
