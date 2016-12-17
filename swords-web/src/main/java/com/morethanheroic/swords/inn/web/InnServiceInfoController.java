package com.morethanheroic.swords.inn.web;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.inn.service.InnServiceAvailabilityCalculator;
import com.morethanheroic.swords.inn.service.response.service.InnServiceAvailabilityResponseBuilder;
import com.morethanheroic.swords.inn.service.response.service.domain.InnServiceAvailabilityResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the information about the inn.
 */
@RestController
@RequiredArgsConstructor
public class InnServiceInfoController {

    private final InnServiceAvailabilityCalculator innServiceAvailabilityCalculator;
    private final InnServiceAvailabilityResponseBuilder innServiceAvailabilityResponseBuilder;

    @GetMapping(value = "/inn/info")
    public Response exploreInfo(UserEntity userEntity) {
        return innServiceAvailabilityResponseBuilder.build(
                InnServiceAvailabilityResponseBuilderConfiguration.builder()
                        .services(innServiceAvailabilityCalculator.getAvailableServices(userEntity))
                        .userEntity(userEntity)
                        .build()
        );
    }
}
