package com.morethanheroic.swords.shop.view.response.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.inventory.service.pouch.domain.MoneyPouch;
import com.morethanheroic.swords.shop.view.response.domain.PlayerMoneyPartialResponse;
import com.morethanheroic.swords.shop.view.response.domain.configuration.PlayerMoneyPartialResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class PlayerMoneyPartialResponseBuilder implements PartialResponseBuilder<PlayerMoneyPartialResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(PlayerMoneyPartialResponseBuilderConfiguration playerMoneyPartialResponseBuilderConfiguration) {
        final MoneyPouch moneyPouch = playerMoneyPartialResponseBuilderConfiguration.getMoneyPouch();

        return PlayerMoneyPartialResponse.builder()
                .bronze(moneyPouch.getBronze())
                .silver(moneyPouch.getSilver())
                .gold(moneyPouch.getGold())
                .build();
    }
}
