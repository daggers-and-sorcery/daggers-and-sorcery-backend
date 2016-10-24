package com.morethanheroic.swords.market.view.controller.sell;

import javax.validation.Valid;

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

    private final MarketService marketService;
    private final ItemDefinitionCache itemDefinitionCache;

    @PostMapping("/market/sell")
    public Response sellItem(final UserEntity userEntity, @RequestBody @Valid final SellItemData sellItemData) {

        marketService.sellToMarket(userEntity, itemDefinitionCache.getDefinition(sellItemData.getItem()), calculatePrice(sellItemData));

        return null;
    }

    //TODO: Moce this to somewhere else. Maybe create a calculator sercvice for monay calculation? How?
    private int calculatePrice(final SellItemData sellItemData) {
        return sellItemData.getPriceBronze() + sellItemData.getPriceSilver() * 100 + sellItemData.getPriceGold() * 100 * 100;
    }
}
