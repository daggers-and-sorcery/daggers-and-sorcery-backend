package com.morethanheroic.swords.shop.view.response;

import com.morethanheroic.swords.item.service.response.ItemEntryResponseBuilder;
import com.morethanheroic.swords.response.domain.Response;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.shop.domain.ShopDefinition;
import com.morethanheroic.swords.shop.domain.ShopEntity;
import com.morethanheroic.swords.shop.domain.ShopItem;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShopItemListResponseBuilder {

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private ItemEntryResponseBuilder itemEntryResponseBuilder;

    public Response build(UserEntity userEntity, ShopEntity shopEntity) {
        Response response = responseFactory.newResponse(userEntity);

        response.setData("shopDefinition", buildShopDefinition(shopEntity.getShopDefinition()));
        response.setData("itemList", buildItemList(shopEntity.getAllItemsInShop()));

        return response;
    }

    private Map<String, Object> buildShopDefinition(ShopDefinition shopDefinition) {
        Map<String, Object> result = new HashMap<>();

        result.put("id", shopDefinition.getId());
        result.put("name", shopDefinition.getName());

        return result;
    }

    private List<Map<String, Object>> buildItemList(List<ShopItem> itemsInShop) {
        List<Map<String, Object>> result = new ArrayList<>();

        for (ShopItem shopItem : itemsInShop) {
            Map<String, Object> item = new HashMap<>();

            item.put("itemDefinition", itemEntryResponseBuilder.buildItemEntry(shopItem.getItem()));
            item.put("itemPrice", 1000);
            item.put("itemAmount", shopItem.getItemAmount());

            result.add(item);
        }

        return result;
    }
}
