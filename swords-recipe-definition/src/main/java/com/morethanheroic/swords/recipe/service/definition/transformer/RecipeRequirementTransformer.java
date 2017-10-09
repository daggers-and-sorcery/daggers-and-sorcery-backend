package com.morethanheroic.swords.recipe.service.definition.transformer;

import com.morethanheroic.definition.transformer.DefinitionListTransformer;
import com.morethanheroic.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.recipe.domain.RecipeRequirement;
import com.morethanheroic.swords.recipe.service.definition.loader.domain.RawRecipeItemRequirement;
import com.morethanheroic.swords.recipe.service.definition.loader.domain.RawRecipeRequirement;
import com.morethanheroic.swords.recipe.service.definition.loader.domain.RawRecipeResourceRequirement;
import com.morethanheroic.swords.recipe.service.definition.loader.domain.RawRecipeSkillRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Transform {@link RawRecipeRequirement} to {@link RecipeRequirement} domain objects.
 */
@Service
@RequiredArgsConstructor
public class RecipeRequirementTransformer implements DefinitionTransformer<RecipeRequirement, RawRecipeRequirement>,
        DefinitionListTransformer<List<RecipeRequirement>, List<RawRecipeRequirement>> {

    private final RecipeItemRequirementTransformer recipeItemRequirementTransformer;
    private final RecipeSkillRequirementTransformer recipeSkillRequirementTransformer;
    private final RecipeResourceRequirementTransformer recipeResourceRequirementTransformer;

    @Override
    public RecipeRequirement transform(RawRecipeRequirement rawRecipeRequirement) {
        if (rawRecipeRequirement instanceof RawRecipeItemRequirement) {
            return recipeItemRequirementTransformer.transform((RawRecipeItemRequirement) rawRecipeRequirement);
        } else if(rawRecipeRequirement instanceof RawRecipeResourceRequirement) {
            return recipeResourceRequirementTransformer.transform((RawRecipeResourceRequirement) rawRecipeRequirement);
        }

        return recipeSkillRequirementTransformer.transform((RawRecipeSkillRequirement) rawRecipeRequirement);
    }

    @Override
    public List<RecipeRequirement> transform(List<RawRecipeRequirement> rawDefinition) {
        return rawDefinition.stream().map(this::transform).collect(Collectors.toList());
    }
}
