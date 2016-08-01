package com.morethanheroic.swords.shop.domain;

import com.morethanheroic.entity.domain.Entity;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.money.domain.MoneyType;
import com.morethanheroic.swords.shop.repository.dao.ShopItemDatabaseEntity;
import com.morethanheroic.swords.shop.repository.domain.ShopMapper;
import com.morethanheroic.swords.shop.service.transformer.ShopItemTransformer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.List;

@Getter
@ToString
@Configurable
@RequiredArgsConstructor
public class ShopEntity implements Entity {

    private static final int MINIMUM_BUY_PRICE = 1;

    private final ShopDefinition shopDefinition;

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private ShopItemTransformer shopItemTransformer;

    @Override
    public int getId() {
        return shopDefinition.getId();
    }

    public List<ShopItem> getAllItemsInShop() {
        return shopItemTransformer.transform(shopMapper.getItemsInShop(shopDefinition.getId()));
    }

    public void buyItem(ItemDefinition itemDefinition, int amount) {
        shopMapper.addStock(shopDefinition.getId(), itemDefinition.getId(), amount);
    }

    public void sellItem(ItemDefinition itemDefinition, int amount) {
        final ShopItemDatabaseEntity shopItemDatabaseEntity = shopMapper.getItemInShop(shopDefinition.getId(), itemDefinition.getId());

        if (shopItemDatabaseEntity.getItemAmount() - amount <= 0) {
            shopMapper.deleteStock(shopDefinition.getId(), itemDefinition.getId());
        } else {
            shopMapper.removeStock(shopDefinition.getId(), itemDefinition.getId(), amount);
        }
    }

    public boolean hasItem(ItemDefinition itemDefinition, int amount) {
        final ShopItemDatabaseEntity shopItemDatabaseEntity = shopMapper.getItemInShop(shopDefinition.getId(), itemDefinition.getId());

        return shopItemDatabaseEntity.getItemAmount() >= amount;
    }

    public int getShopSellPrice(ItemDefinition itemDefinition) {
        return itemDefinition.getPriceDefinitionFor(MoneyType.MONEY).getAmount();
    }

    public int getShopBuyPrice(ItemDefinition itemDefinition) {
        return Math.max(MINIMUM_BUY_PRICE, itemDefinition.getPriceDefinitionFor(MoneyType.MONEY).getAmount() / 2);
    }
}
