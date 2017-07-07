package com.morethanheroic.swords.shop.domain;

import com.morethanheroic.entity.domain.Entity;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.shop.repository.dao.ShopItemDatabaseEntity;
import com.morethanheroic.swords.shop.repository.domain.ShopMapper;
import com.morethanheroic.swords.shop.service.transformer.AvailableItemTransformer;
import com.morethanheroic.swords.shop.service.transformer.ShopItemTransformer;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.ArrayList;
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

    @Autowired
    private AvailableItemTransformer availableItemTransformer;

    @Override
    public int getId() {
        return shopDefinition.getId();
    }

    public List<ShopItem> getAllItems(final UserEntity userEntity) {
        final List<ShopItem> items = new ArrayList<>();

        items.addAll(shopItemTransformer.transform(userEntity, shopMapper.getItemsInShop(shopDefinition.getId())));
        items.addAll(availableItemTransformer.transform(userEntity, shopDefinition));

        return items;
    }

    public void buyItem(ItemDefinition itemDefinition, int amount) {
        shopMapper.addStock(shopDefinition.getId(), itemDefinition.getId(), amount);
    }

    public void sellItem(ItemDefinition itemDefinition, int amount) {
        final ShopItemDatabaseEntity shopItemDatabaseEntity = shopMapper.getItemInShop(shopDefinition.getId(), itemDefinition.getId());

        if (shopItemDatabaseEntity != null) {
            if (shopItemDatabaseEntity.getItemAmount() - amount <= 0) {
                shopMapper.deleteStock(shopDefinition.getId(), itemDefinition.getId());
            } else {
                shopMapper.removeStock(shopDefinition.getId(), itemDefinition.getId(), amount);
            }
        }
    }

    public boolean hasItem(final ItemDefinition itemDefinition) {
        final ShopItemDatabaseEntity shopItemDatabaseEntity = shopMapper.getItemInShop(shopDefinition.getId(), itemDefinition.getId());

        return shopItemDatabaseEntity != null ? shopItemDatabaseEntity.getItemAmount() >= 1 : availableItemsHasItem(itemDefinition);

    }

    private boolean availableItemsHasItem(final ItemDefinition itemDefinition) {
        return shopDefinition.getAvailableItems().stream().anyMatch(availableItem -> availableItem.getItem().getId() == itemDefinition.getId());
    }
}
