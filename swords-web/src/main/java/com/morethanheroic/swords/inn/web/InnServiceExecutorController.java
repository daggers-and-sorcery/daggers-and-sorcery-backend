package com.morethanheroic.swords.inn.web;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.inn.domain.ServiceType;
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

    @RequestMapping(value = "/inn/service/{serviceId}", method = RequestMethod.GET)
    public Response executeService(UserEntity userEntity, @PathVariable String serviceId) {
        innServiceServer.serve(ServiceType.valueOf(serviceId), DefaultServingContext.builder()
                .userEntity(userEntity)
                .build()
        );

        //TODO: valid response!
        return null;
    }
}
