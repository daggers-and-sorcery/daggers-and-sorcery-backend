package com.morethanheroic.swords.skill.imbuing.service.domain;

public enum ImbuingResult {

    /**
     * Happens when the result of the jewelcrafting recipe crafting is successful.
     */
    SUCCESSFUL,
    /**
     * Happens when the result of the jewelcrafting recipe crafting is unsuccessful (mainly because the chance percentage is not 100%).
     */
    UNSUCCESSFUL,
    /**
     * Happens when the target recipe is non existing.
     */
    INVALID_RECIPE,
    /**
     * Happens when requirements (mostly skill) are missing to finish the recipe.
     */
    MISSING_REQUIREMENTS,
    /**
     * Happens when ingredients are missing to finish the recipe.
     */
    MISSING_INGREDIENTS,
    /**
     * Happens when the player don't have enough movement points.
     */
    NOT_ENOUGH_MOVEMENT
}
