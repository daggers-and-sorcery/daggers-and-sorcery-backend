package com.morethanheroic.swords.skill.leatherworking.service.event;

import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.service.result.RecipeExperienceAwarder;
import com.morethanheroic.swords.recipe.service.result.RecipeResultAwarder;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//TODO: Event should come from definition files!
/**
 * Evaluate a curing event. Award the resulting experience and items.
 */
@Service
public class CuringEventExecutor {

    @Autowired
    private InventoryFacade inventoryFacade;

    @Autowired
    private SkillEntityFactory skillEntityFactory;

    @Autowired
    private RecipeResultAwarder recipeResultAwarder;

    @Autowired
    private RecipeExperienceAwarder recipeExperienceAwarder;

    public void evaluateEvent(final UserEntity userEntity, final RecipeDefinition recipeDefinition) {
        final InventoryEntity inventoryEntity = inventoryFacade.getInventory(userEntity);
        final SkillEntity skillEntity = skillEntityFactory.getSkillEntity(userEntity);

        recipeResultAwarder.awardRewards(inventoryEntity, recipeDefinition);
        recipeExperienceAwarder.awardExperience(skillEntity, recipeDefinition);
    }
}
