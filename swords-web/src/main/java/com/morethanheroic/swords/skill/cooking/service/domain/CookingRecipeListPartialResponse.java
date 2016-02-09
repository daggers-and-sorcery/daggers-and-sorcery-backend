package com.morethanheroic.swords.skill.cooking.service.domain;

import com.morethanheroic.swords.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CookingRecipeListPartialResponse extends PartialResponse {

    private List<CookingRecipePartialResponse> recipes;
}
