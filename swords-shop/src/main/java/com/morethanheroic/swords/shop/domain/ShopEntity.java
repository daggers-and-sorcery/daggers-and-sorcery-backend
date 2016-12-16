package com.morethanheroic.swords.shop.domain;

import com.morethanheroic.entity.domain.Entity;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.shop.repository.dao.ShopItemDatabaseEntity;
import com.morethanheroic.swords.shop.repository.domain.ShopMapper;
import com.morethanheroic.swords.shop.service.transformer.ShopItemTransformer;
import com.morethanheroic.swords.user.domain.UserEntity;
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

    public List<ShopItem> getAllItems(final UserEntity userEntity) {
        return shopItemTransformer.transform(userEntity, shopMapper.getItemsInShop(shopDefinition.getId()));
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

    public boolean hasItem(ItemDefinition itemDefinition) {
        final ShopItemDatabaseEntity shopItemDatabaseEntity = shopMapper.getItemInShop(shopDefinition.getId(), itemDefinition.getId());

        if (shopItemDatabaseEntity == null) {
            return false;
        }

        return shopItemDatabaseEntity.getItemAmount() >= 1;
    }
}
