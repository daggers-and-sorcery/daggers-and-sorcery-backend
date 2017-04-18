package com.morethanheroic.swords.skill.herblore.view.request;

import lombok.Data;

import javax.validation.constraints.Min;

/**
 * Contains the information of a herblore crafting request.
 */
@Data
public class HerbloreCraftRequest {

    @Min(1)
    private int recipeId;
}
