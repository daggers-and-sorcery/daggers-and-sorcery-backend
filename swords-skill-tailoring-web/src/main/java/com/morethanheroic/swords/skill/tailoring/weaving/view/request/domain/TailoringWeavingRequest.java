package com.morethanheroic.swords.skill.tailoring.weaving.view.request.domain;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class TailoringWeavingRequest {

    @Min(1)
    private int recipeId;
}