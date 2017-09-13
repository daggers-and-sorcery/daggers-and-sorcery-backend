package com.morethanheroic.swords.recipe.service;

import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.recipe.domain.*;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeRequirementEvaluator {

    private final SkillEntityFactory skillEntityFactory;
    private final InventoryEntityFactory inventoryEntityFactory;
    private final GlobalAttributeCalculator globalAttributeCalculator;

    public boolean hasRequirements(final UserEntity userEntity, final RecipeDefinition recipeDefinition) {
        for (RecipeRequirement recipeRequirement : recipeDefinition.getRecipeRequirements()) {
            if (recipeRequirement instanceof RecipeSkillRequirement) {
                final RecipeSkillRequirement recipeSkillRequirement = (RecipeSkillRequirement) recipeRequirement;

                if (skillEntityFactory.getEntity(userEntity).getLevel(recipeSkillRequirement.getSkill()) < recipeSkillRequirement.getAmount()) {
                    return false;
                }
            } else if (recipeRequirement instanceof RecipeItemRequirement) {
                final RecipeItemRequirement recipeItemRequirement = (RecipeItemRequirement) recipeRequirement;

                if (!inventoryEntityFactory.getEntity(userEntity).hasItemAmount(recipeItemRequirement.getItem(), recipeItemRequirement.getAmount())) {
                    return false;
                }
            } else if (recipeRequirement instanceof RecipeResourceRequirement) {
                final RecipeResourceRequirement recipeResourceRequirement = (RecipeResourceRequirement) recipeRequirement;

                if (recipeResourceRequirement.getResource() == RecipeResource.MANA) {
                    final int actualMana = globalAttributeCalculator.calculateActualValue(userEntity, CombatAttribute.MANA).getValue();

                    if (actualMana < recipeResourceRequirement.getAmount()) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
