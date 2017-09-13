package com.morethanheroic.swords.skill.imbuing.view.request.domain;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class ImbuingCraftRequest {

    @Min(1)
    private int recipeId;
}
