package com.morethanheroic.swords.market.service;

import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.market.domain.BuyingResult;
import com.morethanheroic.swords.market.domain.MarketEntity;
import com.morethanheroic.swords.market.repository.repository.MarketMapper;
import com.morethanheroic.swords.money.domain.MoneyType;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BuyingService {

    private final MarketMapper marketMapper;
    private final InventoryEntityFactory inventoryEntityFactory;

    @Transactional
    public BuyingResult buyFromMarket(final UserEntity userEntity, final MarketEntity marketEntity) {
        if (marketEntity == null) {
            return BuyingResult.MARKETING_ENTRY_DOES_NOT_EXISTS;
        }

        final InventoryEntity inventoryEntity = inventoryEntityFactory.getEntity(userEntity.getId());

        if (!inventoryEntity.hasMoneyAmount(MoneyType.MONEY, marketEntity.getPrice())) {
            return BuyingResult.INSUFFICIENT_RESOURCES;
        }

        decreaseOfferAmount(marketEntity);

        decreaseMoneyFromBuyer(inventoryEntity, marketEntity);
        awardItemToBuyer(inventoryEntity, marketEntity);
        awardPriceToSeller(marketEntity);

        log.info("The user is trying to buy the item: " + marketEntity.getItem() + " with price: " + marketEntity.getPrice() + " from the market!");

        return BuyingResult.SUCCESSFUL_TRANSACTION;
    }

    private void decreaseOfferAmount(final MarketEntity marketEntity) {
        if (marketEntity.getAmount() > 1) {
            marketMapper.decreaseAmountOnMarketOffer(marketEntity.getId());
        } else {
            marketMapper.removeMarketOffer(marketEntity.getId());
        }
    }

    private void decreaseMoneyFromBuyer(final InventoryEntity inventoryEntity, final MarketEntity marketEntity) {
        inventoryEntity.decreaseMoneyAmount(MoneyType.MONEY, marketEntity.getPrice());
    }

    private void awardItemToBuyer(final InventoryEntity inventoryEntity, final MarketEntity marketEntity) {
        inventoryEntity.addItem(marketEntity.getItem(), 1);
    }

    private void awardPriceToSeller(final MarketEntity marketEntity) {
        final InventoryEntity sellerInventoryEntity = inventoryEntityFactory.getEntity(marketEntity.getSeller().getId());

        sellerInventoryEntity.increaseMoneyAmount(MoneyType.MONEY, (int) Math.floor(marketEntity.getPrice() * 0.90));
    }
}
