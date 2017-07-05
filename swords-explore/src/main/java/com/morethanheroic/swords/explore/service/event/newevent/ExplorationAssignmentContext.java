package com.morethanheroic.swords.explore.service.event.newevent;

import com.morethanheroic.swords.user.domain.UserEntity;

import lombok.Builder;
import lombok.Getter;

/**
 * A context that can be used while evaluating that an exploration should be assigned to the user.
 */
@Getter
@Builder
public class ExplorationAssignmentContext {

    private final UserEntity userEntity;
}
