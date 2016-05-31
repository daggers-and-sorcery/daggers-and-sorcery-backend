package com.morethanheroic.swords.skill.leatherworking.service.event.impl;

import com.morethanheroic.swords.event.domain.EventType;
import com.morethanheroic.swords.event.service.event.Event;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.service.cache.RecipeDefinitionCache;
import com.morethanheroic.swords.recipe.service.result.RecipeExperienceAwarder;
import com.morethanheroic.swords.recipe.service.result.RecipeResultAwarder;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoxSkinCuredEvent implements Event {

    private static final int FIX_SKIN_CURING_RECIPE_ID = 13;

    @Autowired
    private InventoryFacade inventoryFacade;

    @Autowired
    private SkillEntityFactory skillEntityFactory;

    @Autowired
    private RecipeResultAwarder recipeResultAwarder;

    @Autowired
    private RecipeExperienceAwarder recipeExperienceAwarder;

    @Autowired
    private RecipeDefinitionCache recipeDefinitionCache;

    @Override
    public void processEvent(UserEntity userEntity) {
        final InventoryEntity inventoryEntity = inventoryFacade.getInventory(userEntity);
        final SkillEntity skillEntity = skillEntityFactory.getSkillEntity(userEntity);
        final RecipeDefinition recipeDefinition = recipeDefinitionCache.getDefinition(FIX_SKIN_CURING_RECIPE_ID);

        recipeResultAwarder.awardRewards(inventoryEntity, recipeDefinition);
        recipeExperienceAwarder.awardExperience(skillEntity, recipeDefinition);
    }

    @Override
    public int getId() {
        return 1;
    }

    @Override
    public EventType getType() {
        return EventType.LEATHERWORKING_CURING;
    }

    @Override
    public String getName() {
        return "Fox skin (cured)";
    }

    @Override
    public int getLength() {
        return 3 * 60 * 1000;
    }
}
