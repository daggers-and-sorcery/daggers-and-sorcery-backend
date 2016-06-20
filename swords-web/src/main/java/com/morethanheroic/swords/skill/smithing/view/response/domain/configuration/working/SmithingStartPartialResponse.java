package com.morethanheroic.swords.skill.smithing.view.response.domain.configuration.working;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.skill.smithing.domain.SmithingResult;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SmithingStartPartialResponse extends PartialResponse {

    private SmithingResult result;
}
