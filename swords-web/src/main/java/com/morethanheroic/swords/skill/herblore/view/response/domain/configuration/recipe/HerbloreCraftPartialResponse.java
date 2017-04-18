package com.morethanheroic.swords.skill.herblore.view.response.domain.configuration.recipe;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.skill.herblore.service.recipes.domain.HerbloreResult;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class HerbloreCraftPartialResponse extends PartialResponse {

    private final HerbloreResult result;
}
