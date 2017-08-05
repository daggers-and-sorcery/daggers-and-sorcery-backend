package com.morethanheroic.swords.skill.jewelcrafting.recipe.view.response.service.domain;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.skill.jewelcrafting.service.recipe.domain.JewelcraftingResult;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JewelcraftingCraftPartialResponse extends PartialResponse {

    private final JewelcraftingResult jewelcraftingResult;
}
