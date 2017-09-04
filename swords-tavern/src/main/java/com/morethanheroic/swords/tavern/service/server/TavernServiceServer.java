package com.morethanheroic.swords.tavern.service.server;

import com.morethanheroic.swords.tavern.domain.service.ServiceType;
import com.morethanheroic.swords.tavern.service.server.context.ServingContext;
import com.morethanheroic.swords.tavern.service.server.executor.ServingExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
public class TavernServiceServer {

    private final Map<ServiceType, ServingExecutor<ServingContext>> executorMap;

    public TavernServiceServer(List<ServingExecutor> servingExecutors) {
        final Map<ServiceType, ServingExecutor<ServingContext>> executorMap = new EnumMap<>(ServiceType.class);

        for (ServingExecutor<ServingContext> servingExecutor : servingExecutors) {
            executorMap.put(servingExecutor.supportedService(), servingExecutor);
        }

        this.executorMap = Collections.unmodifiableMap(executorMap);
    }

    @Transactional
    public boolean serve(ServiceType serviceType, ServingContext servingContext) {
        final ServingExecutor<ServingContext> servingExecutor = executorMap.get(serviceType);

        if (servingExecutor.canExecute(servingContext)) {
            servingExecutor.executeServing(servingContext);

            return true;
        }

        return false;
    }
}
