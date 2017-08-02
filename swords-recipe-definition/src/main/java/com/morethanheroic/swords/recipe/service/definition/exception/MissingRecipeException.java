package com.morethanheroic.swords.recipe.service.definition.exception;

/**
 * Thrown when a recipe is missing when handling recipes.
 */
public class MissingRecipeException extends RuntimeException {

    public MissingRecipeException(int recipeId) {
        super("Missing recipe with id: " + recipeId);
    }
}
