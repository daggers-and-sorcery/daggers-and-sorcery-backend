package com.morethanheroic.swords.skill.fletching.view.request.domain;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class FletchingCreateRequest {

    @Min(1)
    private int recipeId;
}
