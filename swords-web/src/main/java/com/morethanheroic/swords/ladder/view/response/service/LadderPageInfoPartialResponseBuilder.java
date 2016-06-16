package com.morethanheroic.swords.ladder.view.response.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.ladder.view.response.domain.LadderPageInfoPartialResponse;
import com.morethanheroic.swords.ladder.view.response.domain.configuration.LadderResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class LadderPageInfoPartialResponseBuilder implements PartialResponseBuilder<LadderResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(LadderResponseBuilderConfiguration ladderResponseBuilderConfiguration) {
        return LadderPageInfoPartialResponse.builder()
                .pageCount(ladderResponseBuilderConfiguration.getPageCount())
                .build();
    }
}
