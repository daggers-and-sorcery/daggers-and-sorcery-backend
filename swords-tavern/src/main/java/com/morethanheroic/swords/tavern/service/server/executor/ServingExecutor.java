package com.morethanheroic.swords.tavern.service.server.executor;

import com.morethanheroic.swords.tavern.domain.service.ServiceType;
import com.morethanheroic.swords.tavern.service.server.context.ServingContext;

public interface ServingExecutor<T extends ServingContext> {

    boolean canExecute(final T servingContext);
    void executeServing(final T servingContext);
    ServiceType supportedService();
}
