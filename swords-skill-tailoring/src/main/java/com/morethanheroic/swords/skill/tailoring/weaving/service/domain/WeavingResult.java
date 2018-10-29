package com.morethanheroic.swords.skill.tailoring.weaving.service.domain;

public enum WeavingResult {

    /**
     * Happens when the result of the weaving recipe crafting is successful.
     */
    SUCCESSFUL,
    /**
     * Happens when the result of the weaving recipe crafting is unsuccessful (mainly because the chance percentage is not 100%).
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
