package com.morethanheroic.swords.quest.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Shows the state of a quest.
 */
@Getter
@RequiredArgsConstructor
public enum QuestState {

    /**
     * The quest is not started.
     */
    NOT_STARTED("Not started"),

    /**
     * The quest is in progress.
     */
    STARTED("Started"),

    /**
     * The quest is already completed.
     */
    COMPLETED("Completed");

    private final String name;
}
