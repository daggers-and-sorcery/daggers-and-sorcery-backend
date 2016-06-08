package com.morethanheroic.swords.skill.leatherworking.view.request.domain.tanning;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class TanningCreateRequest {

    @Min(1)
    private int recipeId;
}
