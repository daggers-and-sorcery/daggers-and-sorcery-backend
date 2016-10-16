package com.morethanheroic.swords.skill.fletching.view.response.domain;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.skill.fletching.domain.FletchingResult;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FletchingCreatePartialResponse extends PartialResponse {

    private final FletchingResult fletchingResult;
}
