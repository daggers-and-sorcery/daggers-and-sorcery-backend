package com.morethanheroic.swords.inn.web;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.inn.domain.service.ServiceType;
import com.morethanheroic.swords.inn.service.response.order.InnServiceOrderResponseBuilder;
import com.morethanheroic.swords.inn.service.response.order.domain.InnServiceOrderResponseBuilderConfiguration;
import com.morethanheroic.swords.inn.service.server.InnServiceServer;
import com.morethanheroic.swords.inn.service.server.context.impl.DefaultServingContext;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InnServiceExecutorController {

    @Autowired
    private InnServiceServer innServiceServer;

    @Autowired
    private InnServiceOrderResponseBuilder innServiceOrderResponseBuilder;

    @RequestMapping(value = "/inn/service/{serviceId}", method = RequestMethod.GET)
    public Response executeService(UserEntity userEntity, @PathVariable String serviceId) {
        final boolean result = innServiceServer.serve(ServiceType.valueOf(serviceId),
            DefaultServingContext.builder()
                .userEntity(userEntity)
                .build()
        );

        return innServiceOrderResponseBuilder.build(
            InnServiceOrderResponseBuilderConfiguration.builder()
                .userEntity(userEntity)
                .successful(result)
                .build()
        );
    }
}
