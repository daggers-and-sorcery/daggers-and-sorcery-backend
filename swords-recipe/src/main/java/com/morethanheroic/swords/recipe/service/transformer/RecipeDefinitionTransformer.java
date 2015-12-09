package com.morethanheroic.swords.recipe.service.transformer;

import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.service.loader.domain.RawRecipeDefinition;
import org.springframework.stereotype.Service;

@Service
public class RecipeDefinitionTransformer implements DefinitionTransformer<RecipeDefinition, RawRecipeDefinition> {

    @Override
    public RecipeDefinition transform(RawRecipeDefinition rawDefinition) {
        return null;
    }
}
