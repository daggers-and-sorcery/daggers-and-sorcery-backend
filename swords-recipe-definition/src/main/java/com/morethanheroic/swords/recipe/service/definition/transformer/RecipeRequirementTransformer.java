package com.morethanheroic.swords.recipe.service.definition.transformer;

import com.morethanheroic.swords.definition.transformer.DefinitionListTransformer;
import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.recipe.domain.RecipeRequirement;
import com.morethanheroic.swords.recipe.service.definition.loader.domain.RawRecipeItemRequirement;
import com.morethanheroic.swords.recipe.service.definition.loader.domain.RawRecipeRequirement;
import com.morethanheroic.swords.recipe.service.definition.loader.domain.RawRecipeSkillRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Transform {@link RawRecipeRequirement} to {@link RecipeRequirement} domain objects.
 */
@Service
public class RecipeRequirementTransformer implements DefinitionTransformer<RecipeRequirement, RawRecipeRequirement>,
        DefinitionListTransformer<List<RecipeRequirement>, List<RawRecipeRequirement>> {

    @Autowired
    private RecipeItemRequirementTransformer recipeItemRequirementTransformer;

    @Autowired
    private RecipeSkillRequirementTransformer recipeSkillRequirementTransformer;

    @Override
    public RecipeRequirement transform(RawRecipeRequirement rawDefinition) {
        if (rawDefinition instanceof RawRecipeItemRequirement) {
            return recipeItemRequirementTransformer.transform((RawRecipeItemRequirement) rawDefinition);
        }

        return recipeSkillRequirementTransformer.transform((RawRecipeSkillRequirement) rawDefinition);
    }

    @Override
    public List<RecipeRequirement> transform(List<RawRecipeRequirement> rawDefinition) {
        return rawDefinition.stream().map(this::transform).collect(Collectors.toList());
    }
}
