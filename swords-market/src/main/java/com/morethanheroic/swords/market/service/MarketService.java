package com.morethanheroic.swords.market.service;

import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.market.domain.MarketEntity;
import com.morethanheroic.swords.market.repository.repository.MarketMapper;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<ItemDefinition> getAllItemWithMarketOffer() {
        final List<Integer> allItems = marketMapper.getAllItemId();

        return Collections.unmodifiableList(
                allItems.stream()
                        .map(itemDefinitionCache::getDefinition)
                        .collect(Collectors.toList())
        );
    }

    public List<MarketEntity> getAllMarketOfferForItem(final ItemDefinition itemDefinition) {
        return null;
    }
}
