package com.morethanheroic.swords.skill.leatherworking.service.event.impl;

import com.morethanheroic.swords.event.domain.Event;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.service.cache.RecipeDefinitionCache;
import com.morethanheroic.swords.skill.leatherworking.service.event.CuringEventExecutor;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeerSkinCuredEvent implements Event {

    private static final int DEER_SKIN_CURING_RECIPE_ID = 14;
    private static final int DEER_SKIN_EVENT_ID = 2;

    @Autowired
    private CuringEventExecutor curingEventExecutor;

    private final RecipeDefinition deerSkinCuringRecipe;

    @Autowired
    public DeerSkinCuredEvent(final RecipeDefinitionCache recipeDefinitionCache) {
        deerSkinCuringRecipe = recipeDefinitionCache.getDefinition(DEER_SKIN_CURING_RECIPE_ID);
    }

    @Override
    public void processEvent(UserEntity userEntity) {
        curingEventExecutor.evaluateEvent(userEntity, deerSkinCuringRecipe);
    }

    @Override
    public int getId() {
        return DEER_SKIN_EVENT_ID;
    }
}
