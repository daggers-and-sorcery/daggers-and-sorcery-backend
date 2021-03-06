package com.morethanheroic.swords.explore.service.context;

import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.explore.domain.context.ExplorationContext;
import com.morethanheroic.swords.zone.domain.ExplorationZone;
import com.morethanheroic.swords.user.domain.UserEntity;

public interface ExplorationContextFactory {

    ExplorationContext newExplorationContext(final UserEntity userEntity, final SessionEntity sessionEntity, ExplorationZone location, int nextStage);
}
