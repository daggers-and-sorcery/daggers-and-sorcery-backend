package com.morethanheroic.swords.skill.leatherworking.view.request.domain.working;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class LeatherworkingCreateRequest {

    @Min(1)
    private int recipeId;
}
