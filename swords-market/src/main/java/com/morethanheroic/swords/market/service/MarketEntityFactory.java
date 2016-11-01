package com.morethanheroic.swords.market.service;

import com.morethanheroic.entity.service.factory.EntityFactory;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.market.domain.MarketEntity;
import com.morethanheroic.swords.market.repository.domain.MarketDatabaseEntity;
import com.morethanheroic.swords.market.repository.repository.MarketMapper;
import com.morethanheroic.swords.user.service.UserEntityFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarketEntityFactory implements EntityFactory<MarketEntity> {

    private final MarketMapper marketMapper;
    private final UserEntityFactory userEntityFactory;
    private final ItemDefinitionCache itemDefinitionCache;

    @Override
    public MarketEntity getEntity(final int marketId) {
        final MarketDatabaseEntity marketDatabaseEntity = marketMapper.getMarketInfo(marketId);

        return MarketEntity.builder()
                .id(marketDatabaseEntity.getId())
                .price(marketDatabaseEntity.getPrice())
                .amount(marketDatabaseEntity.getAmount())
                .seller(userEntityFactory.getEntity(marketDatabaseEntity.getSeller()))
                .item(itemDefinitionCache.getDefinition(marketDatabaseEntity.getItem()))
                .build();
    }
}
