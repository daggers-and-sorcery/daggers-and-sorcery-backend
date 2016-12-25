package com.morethanheroic.swords.skill.herblore.view.response.domain.configuration.gathering;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.skill.herblore.service.gathering.domain.GatheringResult;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GatheringStartPartialResponse extends PartialResponse {

    private final GatheringResult result;
}
