package com.morethanheroic.swords.inn.service.server.executor;

import com.morethanheroic.swords.inn.domain.ServiceType;
import com.morethanheroic.swords.inn.service.server.context.ServingContext;

public interface ServingExecutor<T extends ServingContext> {

    boolean canExecute(final T servingContext);
    void executeServing(final T servingContext);
    ServiceType supportedService();
}
