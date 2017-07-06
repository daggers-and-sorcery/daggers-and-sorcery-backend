package com.morethanheroic.swords.shop.view.controller.list;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.inventory.service.pouch.MoneyPouchFactory;
import com.morethanheroic.swords.money.domain.MoneyType;
import com.morethanheroic.swords.shop.domain.ShopDefinition;
import com.morethanheroic.swords.shop.view.response.service.ShopItemListFactory;
import com.morethanheroic.swords.shop.view.response.domain.buy.configuration.ShopBuyListResponseBuilderConfiguration;
import com.morethanheroic.swords.shop.view.response.service.buy.ShopBuyListResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BuyFromShopStockListController {

    private final ShopBuyListResponseBuilder shopBuyListResponseBuilder;
    private final MoneyPouchFactory moneyPouchFactory;
    private final ShopItemListFactory shopItemListFactory;

    @GetMapping("/shop/buylist/{shopId}")
    public Response listStock(final UserEntity userEntity, final @PathVariable("shopId") ShopDefinition shopDefinition) {
        //TODO: Check that the user is in the same city as the shop.

        return shopBuyListResponseBuilder.build(
                ShopBuyListResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .moneyPouch(moneyPouchFactory.newMoneyPouch(userEntity, MoneyType.MONEY))
                        .shopDefinition(shopDefinition)
                        .items(shopItemListFactory.getItemsToBuyByThePlayerInShop(userEntity, shopDefinition))
                        .build()
        );
    }
}
