package com.morethanheroic.swords.market.service;

import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.market.domain.MarketEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j
@Service
public class MarketService {

    public void sellToMarket(final UserEntity userEntity, final ItemDefinition item, final int price) {
        log.info("The user is trying to sell the item: " + item + " with price: " + price + " to the market!");
    }

    public void buyFromMarket(final UserEntity userEntity, final MarketEntity marketEntity) {
        log.info("The user is trying to buy the item: " + marketEntity.getItem() + " with price: " + marketEntity.getPrice() + " from the market!");
    }

    public List<MarketEntity> getAllMarketOffer() {
        return null;
    }
}
