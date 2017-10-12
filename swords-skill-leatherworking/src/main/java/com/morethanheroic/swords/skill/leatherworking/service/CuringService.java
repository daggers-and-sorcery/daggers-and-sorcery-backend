package com.morethanheroic.swords.skill.leatherworking.service;

import com.morethanheroic.swords.attribute.domain.BasicAttribute;
import com.morethanheroic.swords.attribute.service.calc.BasicAttributeCalculator;
import com.morethanheroic.swords.attribute.service.manipulator.UserBasicAttributeManipulator;
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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CuringService {

    private static final int MAXIMUM_CURING_QUEUE_COUNT = 10;
    private static final int CURING_MOVEMENT_POINT_COST = 1;

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

    @Autowired
    private UserBasicAttributeManipulator userBasicAttributeManipulator;

    @Autowired
    private BasicAttributeCalculator basicAttributeCalculator;

    @Transactional(isolation = Isolation.SERIALIZABLE)
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

        if (basicAttributeCalculator.calculateActualValue(userEntity, BasicAttribute.MOVEMENT, false).getValue()  < CURING_MOVEMENT_POINT_COST) {
            return CuringResult.NOT_ENOUGH_MOVEMENT;
        }

        final EventEntity eventEntity = EventEntity.builder()
                .eventId(recipeDefinitionToCuringEventConverter.convert(recipeDefinition))
                .user(userEntity)
                .build();

        eventRegistry.registerEvent(eventEntity);

        recipeIngredientsRemover.removeIngredients(inventoryFacade.getInventory(userEntity), recipeDefinition);

        userBasicAttributeManipulator.decreaseMovement(userEntity, CURING_MOVEMENT_POINT_COST);

        return CuringResult.SUCCESSFUL;
    }
}
