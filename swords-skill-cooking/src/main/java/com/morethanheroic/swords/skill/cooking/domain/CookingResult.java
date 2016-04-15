package com.morethanheroic.swords.skill.cooking.domain;

public enum CookingResult {

    SUCCESSFUL,
    UNSUCCESSFUL,
    /**
     * Happens when for example the user doesn't have enough movement points.
     */
    UNABLE_TO_COOK
}
