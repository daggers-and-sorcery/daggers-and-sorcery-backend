package com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.tanning;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.skill.leatherworking.domain.TanningResult;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TanningResultPartialResponse extends PartialResponse {

    private final TanningResult result;
}
