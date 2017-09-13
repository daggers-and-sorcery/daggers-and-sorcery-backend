package com.morethanheroic.swords.skill.crafting.view.request.domain;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class CraftingRecipeCraftRequest {

    @Min(1)
    private int recipeId;
}
