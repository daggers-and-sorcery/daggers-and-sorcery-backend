package com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.skill.leatherworking.domain.CuringResult;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CuringResultPartialResponse extends PartialResponse {

    private final CuringResult result;
}
