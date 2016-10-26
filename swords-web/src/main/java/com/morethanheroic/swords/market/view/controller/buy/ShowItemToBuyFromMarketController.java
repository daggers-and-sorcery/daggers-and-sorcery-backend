package com.morethanheroic.swords.market.view.controller.buy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.market.service.MarketService;
import com.morethanheroic.swords.market.view.service.buy.ShowItemToBuyResponseBuilder;
import com.morethanheroic.swords.market.view.service.domain.buy.ShowItemToBuyResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ShowItemToBuyFromMarketController {

    private final ShowItemToBuyResponseBuilder showItemToBuyResponseBuilder;
    private final ItemDefinitionCache itemDefinitionCache;
    private final MarketService marketService;

    @GetMapping("/market/show/buy/{itemId}")
    public Response showItemToBuy(final UserEntity userEntity, final int itemId) {
        return showItemToBuyResponseBuilder.build(
            ShowItemToBuyResponseBuilderConfiguration.builder()
                .userEntity(userEntity)
                .listings(marketService.getAllMarketOfferForItem(itemDefinitionCache.getDefinition(itemId)))
                .build()
        );
    }
}
