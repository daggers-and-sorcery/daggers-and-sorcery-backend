package com.morethanheroic.swords.witchhuntersguild.view.guildhall.response.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.witchhuntersguild.domain.WitchhuntersGuildEntity;
import com.morethanheroic.swords.witchhuntersguild.view.guildhall.response.service.domain.WitchhuntersGuildGuildHallPartialResponse;
import com.morethanheroic.swords.witchhuntersguild.view.guildhall.response.service.domain.WitchhuntersGuildGuildHallResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class WitchhuntersGuildGuildHallPartialResponseBuilder implements PartialResponseBuilder<WitchhuntersGuildGuildHallResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(WitchhuntersGuildGuildHallResponseBuilderConfiguration witchhuntersGuildGuildHallResponseBuilderConfiguration) {
        final WitchhuntersGuildEntity witchhuntersGuildEntity = witchhuntersGuildGuildHallResponseBuilderConfiguration.getWitchhuntersGuildEntity();

        return WitchhuntersGuildGuildHallPartialResponse.builder()
                .reputationPoints(witchhuntersGuildEntity.getReputationPoints())
                .rank(witchhuntersGuildEntity.getRank().getName())
                .build();
    }
}
