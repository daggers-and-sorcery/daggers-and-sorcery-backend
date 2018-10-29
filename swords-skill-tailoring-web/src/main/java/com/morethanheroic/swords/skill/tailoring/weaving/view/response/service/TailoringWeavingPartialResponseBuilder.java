package com.morethanheroic.swords.skill.tailoring.weaving.view.response.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.skill.tailoring.weaving.view.response.service.domain.TailoringWeavingPartialResponse;
import com.morethanheroic.swords.skill.tailoring.weaving.view.response.service.domain.TailoringWeavingResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TailoringWeavingPartialResponseBuilder implements PartialResponseBuilder<TailoringWeavingResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(TailoringWeavingResponseBuilderConfiguration tailoringWeavingResponseBuilderConfiguration) {
        return TailoringWeavingPartialResponse.builder()
                .weavingResult(tailoringWeavingResponseBuilderConfiguration.getWeavingResult())
                .build();
    }
}
