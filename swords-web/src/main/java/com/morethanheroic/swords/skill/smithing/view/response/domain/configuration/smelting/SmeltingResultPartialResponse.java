package com.morethanheroic.swords.skill.smithing.view.response.domain.configuration.smelting;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.skill.smithing.domain.SmeltingResult;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SmeltingResultPartialResponse extends PartialResponse {

    private final SmeltingResult result;
}
