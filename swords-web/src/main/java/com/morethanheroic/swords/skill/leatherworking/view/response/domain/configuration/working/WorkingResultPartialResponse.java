package com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.working;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.skill.leatherworking.domain.LeatherworkingResult;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WorkingResultPartialResponse extends PartialResponse {

    private final LeatherworkingResult result;
}
