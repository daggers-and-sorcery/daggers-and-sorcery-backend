package com.morethanheroic.swords.explore.service.event;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationAssignmentContext;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationContext;
import com.morethanheroic.swords.explore.service.event.newevent.ImprovedExplorationEventHandler;
import com.morethanheroic.swords.user.domain.UserEntity;

/**
 * The most basic and oldest implementation of an exploration event. Shouldn't be used anymore.
 *
 * @deprecated Use {@link ImprovedExplorationEventHandler} instead.
 */
@Deprecated
public abstract class ExplorationEventHandler {

    /**
     * Return the id of the event.
     *
     * @return the id of the event
     */
    public abstract int getId();

    /**
     * Explore the event for the given user. This will be an one-shot event so you can't have multiple stages like in other event implementations.
     *
     * @param userEntity the user to run the event for
     * @return the result of the exploration
     * @deprecated Use {@link ImprovedExplorationEventHandler#handleExplore(ExplorationContext)} instead.
     */
    public abstract ExplorationResult explore(UserEntity userEntity);

    /**
     * Return true if the event should be assigned to the user and the user will be able to participate in it.
     *
     * @param explorationAssignmentContext the context of the evaluation
     * @return true if the user can participate in the event
     */
    public boolean shouldAssign(ExplorationAssignmentContext explorationAssignmentContext) {
        return true;
    }
}
