package com.morethanheroic.swords.recipe.service.result;

import com.morethanheroic.swords.attribute.service.manipulator.UserBasicAttributeManipulator;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeResource;
import com.morethanheroic.swords.recipe.domain.RecipeResourceRequirement;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeRequirementsRemover {

    private final UserBasicAttributeManipulator userBasicAttributeManipulator;

    public void removeRequirements(final UserEntity userEntity, RecipeDefinition recipeDefinition) {
        recipeDefinition.getRecipeRequirements().stream()
                .filter(recipeRequirement -> recipeRequirement instanceof RecipeResourceRequirement)
                .map(recipeRequirement -> (RecipeResourceRequirement) recipeRequirement)
                .forEach(recipeResourceRequirement -> {
                    if (recipeResourceRequirement.getResource() == RecipeResource.MANA) {
                        userBasicAttributeManipulator.decreaseMana(userEntity, recipeResourceRequirement.getAmount());
                    }
                });
    }
}
