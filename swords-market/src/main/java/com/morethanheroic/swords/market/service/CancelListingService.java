package com.morethanheroic.swords.market.service;

import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.market.domain.MarketEntity;
import com.morethanheroic.swords.market.repository.repository.MarketMapper;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CancelListingService {

    private final MarketEntityFactory marketEntityFactory;
    private final InventoryEntityFactory inventoryEntityFactory;
    private final MarketMapper marketMapper;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void cancelListing(final UserEntity userEntity, final int marketEntityId) {
        final MarketEntity marketEntity = marketEntityFactory.getEntity(marketEntityId);

        if (marketEntity == null) {
            return;
        }

        final InventoryEntity inventoryEntity = inventoryEntityFactory.getEntity(userEntity.getId());

        inventoryEntity.addItem(marketEntity.getItem(), marketEntity.getAmount());

        marketMapper.removeMarketOffer(marketEntity.getId());
    }
}
