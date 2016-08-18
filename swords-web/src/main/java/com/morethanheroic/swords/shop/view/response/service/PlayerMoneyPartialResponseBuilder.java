package com.morethanheroic.swords.shop.view.response.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.shop.view.response.domain.PlayerMoneyPartialResponse;
import com.morethanheroic.swords.shop.view.response.domain.configuration.PlayerMoneyPartialResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class PlayerMoneyPartialResponseBuilder implements PartialResponseBuilder<PlayerMoneyPartialResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(PlayerMoneyPartialResponseBuilderConfiguration playerMoneyPartialResponseBuilderConfiguration) {
        return PlayerMoneyPartialResponse.builder()
                .bronze(playerMoneyPartialResponseBuilderConfiguration.getBronze())
                .silver(playerMoneyPartialResponseBuilderConfiguration.getSilver())
                .gold(playerMoneyPartialResponseBuilderConfiguration.getGold())
                .build();
    }
}
