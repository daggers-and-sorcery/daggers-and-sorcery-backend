package com.morethanheroic.swords.quest.domain;

/**
 * Shows the state of a quest.
 */
public enum QuestState {

    /**
     * The quest is not started.
     */
    NOT_STARTED,

    /**
     * The quest is in progress.
     */
    STARTED,

    /**
     * The quest is already completed.
     */
    COMPLETED
}
