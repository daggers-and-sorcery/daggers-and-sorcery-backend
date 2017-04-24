package com.morethanheroic.swords.skill.herblore.service.gathering.domain;

/**
 * Contains the result of starting a gathering task.
 */
public enum GatheringResult {

    /**
     * Happens when the player already has the maximum simultaneous gathering tasks running.
     */
    QUEUE_FULL,
    /**
     * Happens when the player doesn't have enough movement points.
     */
    NOT_ENOUGH_MOVEMENT,
    /**
     * Happens when starting the gathering was successful.
     */
    SUCCESSFUL
}
