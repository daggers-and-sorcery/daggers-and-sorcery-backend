package com.morethanheroic.swords.skill.crafting.view.response.service.recipe.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CraftingRecipeCraftResultPartialResponse extends PartialResponse {

    private final boolean successful;
    private final String result;
}
