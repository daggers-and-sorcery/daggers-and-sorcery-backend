package com.morethanheroic.swords.market.service;

import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.market.domain.MarketEntity;
import com.morethanheroic.swords.market.domain.MarketOfferInformationEntry;
import com.morethanheroic.swords.market.repository.domain.MarketDatabaseEntity;
import com.morethanheroic.swords.market.repository.domain.MarketDatabaseInformation;
import com.morethanheroic.swords.market.repository.repository.MarketMapper;
import com.morethanheroic.swords.user.service.UserEntityFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Log4j
@Service
@RequiredArgsConstructor
public class MarketService {

    private final UserEntityFactory userEntityFactory;
    private final MarketMapper marketMapper;
    private final ItemDefinitionCache itemDefinitionCache;

    public List<MarketOfferInformationEntry> getMarketOfferInformation() {
        final List<MarketDatabaseInformation> allItems = marketMapper.getAllItem();

        return Collections.unmodifiableList(
                allItems.stream()
                        .map(
                                (marketDatabaseInformation) -> MarketOfferInformationEntry.builder()
                                        .amount(marketDatabaseInformation.getAmount())
                                        .lowestPrice(marketDatabaseInformation.getPrice())
                                        .item(itemDefinitionCache.getDefinition(marketDatabaseInformation.getItem()))
                                        .build()
                        )
                        .collect(Collectors.toList())
        );
    }

    public List<MarketEntity> getAllMarketOfferForItem(final ItemDefinition itemDefinition) {
        final List<MarketDatabaseEntity> entities = marketMapper.getOffersForItem(itemDefinition.getId());

        return entities.stream()
                .map((entity) -> MarketEntity.builder()
                        .id(entity.getId())
                        .seller(userEntityFactory.getEntity(entity.getSeller()))
                        .amount(entity.getAmount())
                        .price(entity.getPrice())
                        .item(itemDefinitionCache.getDefinition(entity.getItem()))
                        .build()
                )
                .collect(
                        Collectors.toList()
                );
    }
}
