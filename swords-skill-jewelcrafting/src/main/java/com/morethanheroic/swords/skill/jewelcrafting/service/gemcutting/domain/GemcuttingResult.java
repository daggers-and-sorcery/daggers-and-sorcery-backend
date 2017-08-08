package com.morethanheroic.swords.skill.jewelcrafting.service.gemcutting.domain;

/**
 * Contains information about the result of the gemcutting task.
 */
public enum GemcuttingResult {

    /**
     * Happens when the result of the gemcutting is successful.
     */
    SUCCESSFUL,
    /**
     * Happens when the result of the gemcutting is unsuccessful (mainly because the chance percentage is not 100%).
     */
    UNSUCCESSFUL,
    /**
     * Happens when the target recipe is non existing.
     */
    INVALID_EVENT,
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
