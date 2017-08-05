package com.morethanheroic.swords.skill.jewelcrafting.recipe.view.request.domain;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class JewelcraftingCraftRequest {

    @Min(1)
    private int recipeId;
}