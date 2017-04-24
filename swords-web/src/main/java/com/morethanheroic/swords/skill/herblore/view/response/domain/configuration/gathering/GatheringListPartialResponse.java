package com.morethanheroic.swords.skill.herblore.view.response.domain.configuration.gathering;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GatheringListPartialResponse extends PartialResponse {

    private String item;
    private long timeLeft;
    private long fullTime;
}
