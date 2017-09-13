package com.morethanheroic.swords.skill.crafting.view.response.service.recipe;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.view.response.RecipeListPartialResponseBuilder;
import com.morethanheroic.swords.recipe.view.response.domain.configuration.RecipeListPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.crafting.view.response.service.recipe.domain.CraftingRecipeInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.skill.view.response.domain.configuration.SkillLevelPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.view.response.service.SkillLevelPartialResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CraftingRecipeInfoResponseBuilder implements ResponseBuilder<CraftingRecipeInfoResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final RecipeListPartialResponseBuilder recipeListPartialResponseBuilder;
    private final SkillLevelPartialResponseBuilder skillLevelPartialResponseBuilder;
    private final SkillEntityFactory skillEntityFactory;

    @Override
    public Response build(final CraftingRecipeInfoResponseBuilderConfiguration craftingRecipeInfoResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(craftingRecipeInfoResponseBuilderConfiguration.getUserEntity());

        response.setData("recipes", recipeListPartialResponseBuilder.build(
                RecipeListPartialResponseBuilderConfiguration.builder()
                        .userEntity(craftingRecipeInfoResponseBuilderConfiguration.getUserEntity())
                        .recipeType(RecipeType.CRAFTING)
                        .build()
                )
        );
        response.setData("skill", skillLevelPartialResponseBuilder.build(
                SkillLevelPartialResponseBuilderConfiguration.builder()
                        .skillLevel(getCraftingLevel(craftingRecipeInfoResponseBuilderConfiguration.getUserEntity()))
                        .build()
                )
        );

        return response;
    }

    private int getCraftingLevel(final UserEntity userEntity) {
        return skillEntityFactory.getEntity(userEntity).getLevel(SkillType.CRAFTING);
    }
}
