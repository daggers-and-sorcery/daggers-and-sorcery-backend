package com.morethanheroic.swords.witchhuntersguild.view.info.response.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.witchhuntersguild.view.info.response.service.domain.WitchhuntersGuildInfoPartialResponse;
import com.morethanheroic.swords.witchhuntersguild.view.info.response.service.domain.WitchhuntersGuildInfoResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class WitchhuntersGuildInfoPartialResponseBuilder implements PartialResponseBuilder<WitchhuntersGuildInfoResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(WitchhuntersGuildInfoResponseBuilderConfiguration witchhuntersGuildInfoResponseBuilderConfiguration) {
        return WitchhuntersGuildInfoPartialResponse.builder()
                .accessible(witchhuntersGuildInfoResponseBuilderConfiguration.isAccessible())
                .questStarted(witchhuntersGuildInfoResponseBuilderConfiguration.isQuestStarted())
                .build();
    }
}
