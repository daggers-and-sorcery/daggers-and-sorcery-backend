package com.morethanheroic.swords.shop.view.controller.list;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.inventory.service.pouch.MoneyPouchFactory;
import com.morethanheroic.swords.money.domain.MoneyType;
import com.morethanheroic.swords.shop.domain.ShopDefinition;
import com.morethanheroic.swords.shop.view.response.domain.sell.configuration.ShopSellListResponseBuilderConfiguration;
import com.morethanheroic.swords.shop.view.response.service.ShopItemListFactory;
import com.morethanheroic.swords.shop.view.response.service.sell.ShopSellListResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SellToShopStockListController {

    private final ShopSellListResponseBuilder shopSellListResponseBuilder;
    private final MoneyPouchFactory moneyPouchFactory;
    private final ShopItemListFactory shopItemListFactory;

    @GetMapping("/shop/selllist/{shopId}")
    public Response listStock(final UserEntity userEntity, final SessionEntity sessionEntity, final @PathVariable("shopId") ShopDefinition shopDefinition) {
        //TODO: Check that the user is in the same city as the shop.

        return shopSellListResponseBuilder.build(
                ShopSellListResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .sessionEntity(sessionEntity)
                        .moneyPouch(moneyPouchFactory.newMoneyPouch(userEntity, MoneyType.MONEY))
                        .shopDefinition(shopDefinition)
                        .items(shopItemListFactory.getItemsToSellByThePlayerInShop(userEntity, shopDefinition))
                        .build()
        );
    }
}