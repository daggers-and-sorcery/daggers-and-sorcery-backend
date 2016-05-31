package com.morethanheroic.swords.skill.leatherworking.service;

import com.morethanheroic.swords.event.domain.EventEntity;
import com.morethanheroic.swords.event.domain.EventType;
import com.morethanheroic.swords.event.service.EventRegistry;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.service.RecipeIngredientEvaluator;
import com.morethanheroic.swords.recipe.service.RecipeRequirementEvaluator;
import com.morethanheroic.swords.recipe.service.result.RecipeIngredientsRemover;
import com.morethanheroic.swords.skill.leatherworking.domain.CuringResult;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CuringService {

    private static final int MAXIMUM_CURING_QUEUE_COUNT = 10;

    @Autowired
    private EventRegistry eventRegistry;

    @Autowired
    private RecipeIngredientEvaluator recipeIngredientEvaluator;

    @Autowired
    private RecipeRequirementEvaluator recipeRequirementEvaluator;

    @Autowired
    private RecipeDefinitionToCuringEventIdConverter recipeDefinitionToCuringEventConverter;

    @Autowired
    private RecipeIngredientsRemover recipeIngredientsRemover;

    @Autowired
    private InventoryFacade inventoryFacade;

    @Transactional
    public CuringResult cure(final UserEntity userEntity, final RecipeDefinition recipeDefinition) {
        if (recipeDefinition == null) {
            return CuringResult.INVALID_EVENT;
        }

        if (eventRegistry.getRegisteredEventCount(userEntity, EventType.LEATHERWORKING_CURING) >= MAXIMUM_CURING_QUEUE_COUNT) {
            return CuringResult.QUEUE_FULL;
        }

        if (!recipeIngredientEvaluator.hasIngredients(userEntity, recipeDefinition)) {
            return CuringResult.MISSING_INGREDIENTS;
        }

        if (!recipeRequirementEvaluator.hasRequirements(userEntity, recipeDefinition)) {
            return CuringResult.MISSING_REQUIREMENTS;
        }

        if (userEntity.getMovementPoints() <= 0) {
            return CuringResult.NOT_ENOUGH_MOVEMENT;
        }

        recipeIngredientsRemover.removeIngredients(inventoryFacade.getInventory(userEntity), recipeDefinition);

        final EventEntity eventEntity = EventEntity.builder()
                .eventId(recipeDefinitionToCuringEventConverter.convert(recipeDefinition))
                .user(userEntity)
                .build();

        eventRegistry.registerEvent(eventEntity);

        return CuringResult.SUCCESSFUL;
    }
}
