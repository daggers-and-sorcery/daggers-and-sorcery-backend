package com.morethanheroic.swords.skill.tailoring.recipe.view.response.service.domain;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.skill.tailoring.recipe.service.domain.TailoringResult;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TailoringCraftPartialResponse extends PartialResponse {

    private final TailoringResult tailoringResult;
}
