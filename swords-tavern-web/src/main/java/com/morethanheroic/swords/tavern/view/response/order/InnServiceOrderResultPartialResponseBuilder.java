package com.morethanheroic.swords.tavern.view.response.order;

import org.springframework.stereotype.Service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.tavern.view.response.order.domain.InnServiceOrderResponseBuilderConfiguration;
import com.morethanheroic.swords.tavern.view.response.order.domain.InnServiceOrderResultPartialResponse;

@Service
public class InnServiceOrderResultPartialResponseBuilder implements PartialResponseBuilder<InnServiceOrderResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(InnServiceOrderResponseBuilderConfiguration innServiceOrderResponseBuilderConfiguration) {
        return InnServiceOrderResultPartialResponse.builder()
            .successful(innServiceOrderResponseBuilderConfiguration.isSuccessful())
            .build();
    }
}
