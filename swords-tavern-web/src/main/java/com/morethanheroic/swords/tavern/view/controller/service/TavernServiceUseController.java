package com.morethanheroic.swords.tavern.view.controller.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.inn.domain.service.ServiceType;
import com.morethanheroic.swords.tavern.view.response.order.TavernServiceOrderResponseBuilder;
import com.morethanheroic.swords.tavern.view.response.order.domain.InnServiceOrderResponseBuilderConfiguration;
import com.morethanheroic.swords.inn.service.server.TavernServiceServer;
import com.morethanheroic.swords.inn.service.server.context.impl.DefaultServingContext;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TavernServiceUseController {

    private final TavernServiceServer tavernServiceServer;
    private final TavernServiceOrderResponseBuilder tavernServiceOrderResponseBuilder;

    @GetMapping("/inn/service/{serviceId}")
    public Response executeService(UserEntity userEntity, @PathVariable String serviceId) {
        final boolean result = tavernServiceServer.serve(ServiceType.valueOf(serviceId),
                DefaultServingContext.builder()
                        .userEntity(userEntity)
                        .build()
        );

        return tavernServiceOrderResponseBuilder.build(
                InnServiceOrderResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .successful(result)
                        .build()
        );
    }
}
