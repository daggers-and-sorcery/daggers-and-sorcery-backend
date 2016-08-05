package com.morethanheroic.swords.shop.view.controller.list;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.exception.NotFoundException;
import com.morethanheroic.swords.shop.service.ShopEntityFactory;
import com.morethanheroic.swords.shop.service.cache.ShopDefinitionCache;
import com.morethanheroic.swords.shop.view.response.domain.buy.configuration.ShopBuyListResponseBuilderConfiguration;
import com.morethanheroic.swords.shop.view.response.service.ShopItemTypeSorter;
import com.morethanheroic.swords.shop.view.response.service.buy.ShopBuyListResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BuyFromShopStockListController {

    private final ShopDefinitionCache shopDefinitionCache;
    private final ShopEntityFactory shopEntityFactory;
    private final ShopBuyListResponseBuilder shopBuyListResponseBuilder;
    private final ShopItemTypeSorter shopItemTypeSorter;

    @RequestMapping(value = "/shop/buylist/{shopId}", method = RequestMethod.GET)
    public Response listStock(UserEntity userEntity, @PathVariable int shopId) {
        if (!shopDefinitionCache.isDefinitionExists(shopId)) {
            throw new NotFoundException();
        }

        //TODO: Check that the user is in the same city as the shop.

        return shopBuyListResponseBuilder.build(
                ShopBuyListResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .shopDefinition(shopDefinitionCache.getDefinition(shopId))
                        .items(shopItemTypeSorter.sortByType(shopEntityFactory.getEntity(shopId).getAllItems()))
                        .build()
        );
    }
}
