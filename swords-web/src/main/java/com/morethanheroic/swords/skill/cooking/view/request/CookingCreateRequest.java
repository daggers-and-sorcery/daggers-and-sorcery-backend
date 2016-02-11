package com.morethanheroic.swords.skill.cooking.view.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
public class CookingCreateRequest {

    @Min(1)
    private int recipeId;
}
