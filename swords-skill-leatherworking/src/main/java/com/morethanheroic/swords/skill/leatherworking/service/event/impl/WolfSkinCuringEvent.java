package com.morethanheroic.swords.skill.leatherworking.service.event.impl;

import com.morethanheroic.swords.event.domain.Event;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.service.cache.RecipeDefinitionCache;
import com.morethanheroic.swords.skill.leatherworking.service.event.CuringEventExecutor;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WolfSkinCuringEvent implements Event {

    private static final int WOLF_SKIN_CURING_RECIPE_ID = 15;
    private static final int WOLF_SKIN_EVENT_ID = 3;

    @Autowired
    private CuringEventExecutor curingEventExecutor;

    private final RecipeDefinition wolfSkinCuringRecipe;

    @Autowired
    public WolfSkinCuringEvent(final RecipeDefinitionCache recipeDefinitionCache) {
        wolfSkinCuringRecipe = recipeDefinitionCache.getDefinition(WOLF_SKIN_CURING_RECIPE_ID);
    }

    @Override
    public void processEvent(final UserEntity userEntity) {
        curingEventExecutor.evaluateEvent(userEntity, wolfSkinCuringRecipe);
    }

    @Override
    public int getId() {
        return WOLF_SKIN_EVENT_ID;
    }
}
