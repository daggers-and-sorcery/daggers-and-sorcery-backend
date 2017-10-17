package com.morethanheroic.swords.skill.tailoring.weaving.view.response.service.domain;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.skill.tailoring.weaving.service.domain.WeavingResult;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TailoringWeavingPartialResponse extends PartialResponse {

    private final WeavingResult weavingResult;
}
