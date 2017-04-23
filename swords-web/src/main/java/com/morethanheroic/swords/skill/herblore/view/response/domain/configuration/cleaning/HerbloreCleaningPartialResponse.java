package com.morethanheroic.swords.skill.herblore.view.response.domain.configuration.cleaning;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.skill.herblore.service.cleaning.domain.CleaningResult;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class HerbloreCleaningPartialResponse extends PartialResponse {

    private final CleaningResult result;
}
