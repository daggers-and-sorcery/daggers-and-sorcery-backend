package com.morethanheroic.swords.skill.smithing.view.request.domain.smelting;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class SmeltingCreateRequest {

    @Min(1)
    private int recipeId;
}
