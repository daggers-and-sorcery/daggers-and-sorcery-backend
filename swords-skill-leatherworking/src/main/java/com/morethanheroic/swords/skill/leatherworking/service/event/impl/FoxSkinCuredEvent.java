package com.morethanheroic.swords.skill.leatherworking.service.event.impl;

import com.morethanheroic.swords.event.domain.Event;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.service.definition.cache.RecipeDefinitionCache;
import com.morethanheroic.swords.skill.leatherworking.service.event.CuringEventExecutor;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoxSkinCuredEvent implements Event {

    private static final int FOX_SKIN_CURING_RECIPE_ID = 13;
    private static final int FOX_SKIN_EVENT_ID = 1;

    @Autowired
    private CuringEventExecutor curingEventExecutor;

    private final RecipeDefinition foxSkinCuringRecipe;

    @Autowired
    public FoxSkinCuredEvent(final RecipeDefinitionCache recipeDefinitionCache) {
        foxSkinCuringRecipe = recipeDefinitionCache.getDefinition(FOX_SKIN_CURING_RECIPE_ID);
    }

    @Override
    public void processEvent(UserEntity userEntity) {
        curingEventExecutor.evaluateEvent(userEntity, foxSkinCuringRecipe);
    }

    @Override
    public int getId() {
        return FOX_SKIN_EVENT_ID;
    }
}
