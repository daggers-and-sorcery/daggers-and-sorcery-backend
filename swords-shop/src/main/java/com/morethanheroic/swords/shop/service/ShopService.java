package com.morethanheroic.swords.shop.service;

import com.morethanheroic.swords.inventory.domain.IdentificationType;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.domain.ItemType;
import com.morethanheroic.swords.money.domain.MoneyType;
import com.morethanheroic.swords.shop.domain.ShopEntity;
import com.morethanheroic.swords.shop.service.availability.ShopAvailabilityCalculator;
import com.morethanheroic.swords.shop.service.price.ItemBuyPriceCalculator;
import com.morethanheroic.swords.shop.service.price.ItemSellPriceCalculator;
import com.morethanheroic.swords.shop.service.price.domain.ItemPriceCalculationContext;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShopService {

    private final InventoryEntityFactory inventoryEntityFactory;
    private final ItemSellPriceCalculator itemSellPriceCalculator;
    private final ItemBuyPriceCalculator itemBuyPriceCalculator;
    private final ShopAvailabilityCalculator shopAvailabilityCalculator;

    //TODO: Check that the shop is in the same city as the player.
    @Transactional
    public void userBuyItem(final UserEntity userEntity, final ShopEntity shopEntity, final ItemDefinition itemDefinition) {
        if (!shopAvailabilityCalculator.isAvailable(userEntity, shopEntity.getShopDefinition())) {
            log.warn("The player tried to buy in a shop: " + itemDefinition.getId() + " where his access is disabled.");

            return;
        }

        if (!shopEntity.getShopDefinition().getAvailableFeatures().isBuying()) {
            log.warn("The player tried to buy in a shop: " + itemDefinition.getId() + " where buying is disabled.");

            return;
        }

        if (!shopEntity.hasItem(itemDefinition)) {
            log.warn("The player tried to buy an item: " + itemDefinition.getId() + " from the shop while the shop don't have it.");

            return;
        }

        final InventoryEntity inventoryEntity = inventoryEntityFactory.getEntity(userEntity);

        final int itemBuyPrice = itemBuyPriceCalculator.calculateBuyPrice(
                ItemPriceCalculationContext.builder()
                        .userEntity(userEntity)
                        .itemDefinition(itemDefinition)
                        .build()
        );

        //TODO: At the moment we only use money as money, no support for special trades.
        if (itemBuyPrice <= inventoryEntity.getMoneyAmount(MoneyType.MONEY)) {
            log.info("The user bought an item: " + itemDefinition.getId());

            inventoryEntity.decreaseMoneyAmount(MoneyType.MONEY, itemBuyPrice);

            inventoryEntity.addItem(itemDefinition, 1);

            shopEntity.sellItem(itemDefinition, 1);
        } else {
            log.info("The user tried to buy an item: " + itemDefinition.getId() + " but did not had enough money to pay for it.");
        }
    }

    //TODO: Check that the shop is in the same city as the player.
    @Transactional
    public void userSellItem(final UserEntity userEntity, final ShopEntity shopEntity, final ItemDefinition itemDefinition, final IdentificationType itemIdentificationType) {
        if (!shopAvailabilityCalculator.isAvailable(userEntity, shopEntity.getShopDefinition())) {
            log.warn("The player tried to buy in a shop: " + itemDefinition.getId() + " where his access is disabled.");

            return;
        }

        if (!shopEntity.getShopDefinition().getAvailableFeatures().isSelling()) {
            log.warn("The player tried to sell in a shop: " + itemDefinition.getId() + " where selling is disabled.");

            return;
        }

        if (itemDefinition.getType() == ItemType.MONEY) {
            log.warn("The player tried to sell an item with money type! The item was: " + itemDefinition.getId());

            return;
        }

        final InventoryEntity inventoryEntity = inventoryEntityFactory.getEntity(userEntity);

        if (inventoryEntity.hasItem(itemDefinition)) {
            log.info("The user sold an item: " + itemDefinition.getId() + " to the shop.");

            inventoryEntity.increaseMoneyAmount(MoneyType.MONEY, itemSellPriceCalculator.calculateSellPrice(
                    ItemPriceCalculationContext.builder()
                            .userEntity(userEntity)
                            .itemDefinition(itemDefinition)
                            .build()
            ));

            inventoryEntity.removeItem(itemDefinition, 1, itemIdentificationType);

            shopEntity.buyItem(itemDefinition, 1);
        } else {
            log.info("The player tried to sell an item: " + itemDefinition.getId() + " he don't have to the shop!");
        }
    }
}
