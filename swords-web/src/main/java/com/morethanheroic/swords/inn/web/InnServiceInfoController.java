package com.morethanheroic.swords.inn.web;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.inn.service.InnServiceAvailabilityCalculator;
import com.morethanheroic.swords.inn.service.response.service.InnServiceAvailabilityResponseBuilder;
import com.morethanheroic.swords.inn.service.response.service.domain.InnServiceAvailabilityResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InnServiceInfoController {

    @Autowired
    private InnServiceAvailabilityCalculator innServiceAvailabilityCalculator;

    @Autowired
    private InnServiceAvailabilityResponseBuilder innServiceAvailabilityResponseBuilder;

    @RequestMapping(value = "/inn/info", method = RequestMethod.GET)
    public Response exploreInfo(UserEntity userEntity) {
        return innServiceAvailabilityResponseBuilder.build(
                InnServiceAvailabilityResponseBuilderConfiguration.builder()
                        .services(innServiceAvailabilityCalculator.getAvailableServices())
                        .userEntity(userEntity)
                        .build()
        );
    }
}
