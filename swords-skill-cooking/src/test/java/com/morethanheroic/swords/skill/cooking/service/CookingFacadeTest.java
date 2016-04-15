package com.morethanheroic.swords.skill.cooking.service;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeIngredient;
import com.morethanheroic.swords.recipe.service.RecipeFacade;
import com.morethanheroic.swords.skill.cooking.domain.CookingResult;
import com.morethanheroic.swords.skill.service.SkillFacade;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CookingFacadeTest {

    @Mock
    private RecipeFacade recipeFacade;

    @Mock
    private InventoryFacade inventoryFacade;

    @Mock
    private SkillFacade skillFacade;

    @Mock
    private Random random;

    @Mock
    private UserEntity userEntity;

    @InjectMocks
    private CookingFacade underTest;

    @Test
    public void testCookingWorksSuccessfulWhenEverythingIsRight() {
        final RecipeDefinition recipeDefinition = buildSimpleTestRecipeDefinition();
        when(userEntity.getMovementPoints()).thenReturn(10);
        when(recipeFacade.hasRecipeLearned(userEntity, recipeDefinition)).thenReturn(true);

        CookingResult cookingResult = underTest.cook(userEntity, recipeDefinition);

        assertThat(cookingResult, is(CookingResult.SUCCESSFUL));
    }

    @Test
    public void testCookingFailsWhenUserDoesntHaveEnoughMovementPoints() {
        final RecipeDefinition recipeDefinition = buildSimpleTestRecipeDefinition();
        when(userEntity.getMovementPoints()).thenReturn(0);
        when(recipeFacade.hasRecipeLearned(userEntity, recipeDefinition)).thenReturn(true);

        CookingResult cookingResult = underTest.cook(userEntity, recipeDefinition);

        assertThat(cookingResult, is(CookingResult.UNABLE_TO_COOK));
    }

    @Test
    public void testCookingFailsWhenNotMeetChance() {
        final RecipeDefinition recipeDefinition = RecipeDefinition.builder()
                .chance(0)
                .id(1)
                .name("Test")
                .recipeExperiences(Collections.emptyList())
                .recipeIngredients(Collections.emptyList())
                .recipeRewards(Collections.emptyList())
                .recipeRequirements(Collections.emptyList())
                .build();
        when(userEntity.getMovementPoints()).thenReturn(1);
        when(recipeFacade.hasRecipeLearned(userEntity, recipeDefinition)).thenReturn(true);

        CookingResult cookingResult = underTest.cook(userEntity, recipeDefinition);

        assertThat(cookingResult, is(CookingResult.UNSUCCESSFUL));
    }

    @Test
    public void testCookingFailsWhenIngredientsNotFound() {
        final RecipeDefinition recipeDefinition = RecipeDefinition.builder()
                .chance(100)
                .id(1)
                .name("Test")
                .recipeExperiences(Collections.emptyList())
                .recipeIngredients(Lists.newArrayList(
                        RecipeIngredient.builder()
                                .id(10)
                                .amount(3)
                                .build()
                ))
                .recipeRewards(Collections.emptyList())
                .recipeRequirements(Collections.emptyList())
                .build();
        when(userEntity.getMovementPoints()).thenReturn(1);
        when(recipeFacade.hasRecipeLearned(userEntity, recipeDefinition)).thenReturn(true);
        final InventoryEntity inventoryEntity = mock(InventoryEntity.class);
        when(inventoryFacade.getInventory(userEntity)).thenReturn(inventoryEntity);

        CookingResult cookingResult = underTest.cook(userEntity, recipeDefinition);

        assertThat(cookingResult, is(CookingResult.UNABLE_TO_COOK));
    }

    private RecipeDefinition buildSimpleTestRecipeDefinition() {
        return RecipeDefinition.builder()
                .chance(100)
                .id(1)
                .name("Test")
                .recipeExperiences(Collections.emptyList())
                .recipeIngredients(Collections.emptyList())
                .recipeRewards(Collections.emptyList())
                .recipeRequirements(Collections.emptyList())
                .build();
    }
}
