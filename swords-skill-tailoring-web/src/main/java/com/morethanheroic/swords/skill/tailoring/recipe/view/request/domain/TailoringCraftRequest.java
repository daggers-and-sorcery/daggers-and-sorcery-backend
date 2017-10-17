package com.morethanheroic.swords.skill.tailoring.recipe.view.request.domain;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class TailoringCraftRequest {

    @Min(1)
    private int recipeId;
}