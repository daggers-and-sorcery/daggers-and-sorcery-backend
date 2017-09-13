package com.morethanheroic.swords.skill.crafting.view.request.domain;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class CraftingResourceCraftRequest {

    @Min(1)
    private int recipeId;
}
