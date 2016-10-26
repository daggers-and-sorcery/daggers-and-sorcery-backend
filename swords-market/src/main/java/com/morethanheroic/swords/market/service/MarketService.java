package com.morethanheroic.swords.market.service;

import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.market.domain.MarketEntity;
import com.morethanheroic.swords.market.domain.MarketOfferInformationEntry;
import com.morethanheroic.swords.market.repository.domain.MarketDatabaseInformation;
import com.morethanheroic.swords.market.repository.repository.MarketMapper;
import com.morethanheroic.swords.user.domain.UserEntity;
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

    private final MarketMapper marketMapper;
    private final ItemDefinitionCache itemDefinitionCache;

    public void sellToMarket(final UserEntity userEntity, final ItemDefinition item, final int price) {
        log.info("The user is trying to sell the item: " + item + " with price: " + price + " to the market!");
    }

    public void buyFromMarket(final UserEntity userEntity, final MarketEntity marketEntity) {
        log.info("The user is trying to buy the item: " + marketEntity.getItem() + " with price: " + marketEntity.getPrice() + " from the market!");
    }

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
        return null;
    }
}
