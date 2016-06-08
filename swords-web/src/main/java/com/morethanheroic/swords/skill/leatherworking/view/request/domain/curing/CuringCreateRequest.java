package com.morethanheroic.swords.skill.leatherworking.view.request.domain.curing;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class CuringCreateRequest {

    @Min(1)
    private int recipeId;
}
