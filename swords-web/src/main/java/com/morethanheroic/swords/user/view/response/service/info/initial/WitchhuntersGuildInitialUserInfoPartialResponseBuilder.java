package com.morethanheroic.swords.user.view.response.service.info.initial;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.user.view.response.service.info.initial.domain.configuration.InitialUserInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.user.view.response.service.info.initial.domain.response.WitchhuntersGuildInitialUserInfoPartialResponse;
import org.springframework.stereotype.Service;

@Service
public class WitchhuntersGuildInitialUserInfoPartialResponseBuilder implements PartialResponseBuilder<InitialUserInfoResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(InitialUserInfoResponseBuilderConfiguration initialUserInfoResponseBuilderConfiguration) {
        return WitchhuntersGuildInitialUserInfoPartialResponse.builder()
                .witchhuntersGuildUnlocked(initialUserInfoResponseBuilderConfiguration.isWitchhuntersGuildUnlocked())
                .build();
    }
}
