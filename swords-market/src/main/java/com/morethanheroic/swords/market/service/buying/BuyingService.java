package com.morethanheroic.swords.market.service.buying;

import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.market.domain.BuyingResult;
import com.morethanheroic.swords.market.domain.MarketEntity;
import com.morethanheroic.swords.market.repository.repository.MarketMapper;
import com.morethanheroic.swords.market.service.MarketEntityFactory;
import com.morethanheroic.swords.market.service.selling.fee.SellingFeeCalculator;
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
    private final MarketEntityFactory marketEntityFactory;
    private final SellingFeeCalculator sellingFeeCalculator;

    @Transactional
    public BuyingResult buyFromMarket(final UserEntity userEntity, final int marketEntityId) {
        final MarketEntity marketEntity = marketEntityFactory.getEntity(marketEntityId);

        if (marketEntity == null) {
            return BuyingResult.MARKETING_ENTRY_DOES_NOT_EXISTS;
        }

        final InventoryEntity inventoryEntity = inventoryEntityFactory.getEntity(userEntity);

        if (!inventoryEntity.hasMoneyAmount(MoneyType.MONEY, marketEntity.getPrice())) {
            return BuyingResult.INSUFFICIENT_RESOURCES;
        }

        decreaseOfferAmount(marketEntity);

        decreaseMoneyFromBuyer(inventoryEntity, marketEntity);
        awardItemToBuyer(inventoryEntity, marketEntity);
        awardMoneyToSeller(marketEntity);

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

    private void awardMoneyToSeller(final MarketEntity marketEntity) {
        inventoryEntityFactory.getEntity(marketEntity.getSeller())
                .increaseMoneyAmount(MoneyType.MONEY, calculateMoneyForSelling(marketEntity.getSeller(), marketEntity));
    }

    private int calculateMoneyForSelling(final UserEntity seller, final MarketEntity marketEntity) {
        return (int) Math.floor(marketEntity.getPrice() - sellingFeeCalculator.calculateSellingFee(seller, marketEntity));
    }
}
