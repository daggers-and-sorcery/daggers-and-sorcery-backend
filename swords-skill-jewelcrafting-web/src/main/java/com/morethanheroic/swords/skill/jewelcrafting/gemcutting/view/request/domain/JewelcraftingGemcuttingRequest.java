package com.morethanheroic.swords.skill.jewelcrafting.gemcutting.view.request.domain;

import lombok.Data;

import javax.validation.constraints.Min;

/**
 * Contains the information of a gemcutting request.
 */
@Data
public class JewelcraftingGemcuttingRequest {

    @Min(1)
    private int recipeId;
}
