package com.morethanheroic.swords.inn.service.server;

import com.morethanheroic.swords.inn.domain.service.ServiceType;
import com.morethanheroic.swords.inn.service.server.context.ServingContext;
import com.morethanheroic.swords.inn.service.server.executor.ServingExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
public class InnServiceServer {

    @Autowired
    private List<ServingExecutor> servingExecutors;

    private Map<ServiceType, ServingExecutor<ServingContext>> executorMap;

    @PostConstruct
    private void initialize() {
        executorMap = new EnumMap<>(ServiceType.class);

        for (ServingExecutor servingExecutor : servingExecutors) {
            executorMap.put(servingExecutor.supportedService(), servingExecutor);
        }
    }

    @Transactional
    public boolean serve(ServiceType serviceType, ServingContext servingContext) {
        final ServingExecutor servingExecutor = executorMap.get(serviceType);

        if (servingExecutor.canExecute(servingContext)) {
            servingExecutor.executeServing(servingContext);

            return true;
        }

        return false;
    }
}
