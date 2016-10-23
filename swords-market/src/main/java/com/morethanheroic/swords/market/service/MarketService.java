package com.morethanheroic.swords.market.service;

import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.market.domain.MarketEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketService {

    public void sellToMarket(final UserEntity userEntity, final ItemDefinition item, final int price) {

    }

    public void buyFromMarket(final UserEntity userEntity, final MarketEntity marketEntity) {

    }

    public List<MarketEntity> getAllMarketOffer() {
        return null;
    }
}
