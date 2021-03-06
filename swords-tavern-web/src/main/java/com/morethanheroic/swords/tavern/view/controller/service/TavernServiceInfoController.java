package com.morethanheroic.swords.tavern.view.controller.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.tavern.service.InnServiceAvailabilityCalculator;
import com.morethanheroic.swords.tavern.view.response.service.TavernServiceAvailabilityResponseBuilder;
import com.morethanheroic.swords.tavern.view.response.service.domain.TavernServiceAvailabilityResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the information about the tavern.
 */
@RestController
@RequiredArgsConstructor
public class TavernServiceInfoController {

    private final InnServiceAvailabilityCalculator innServiceAvailabilityCalculator;
    private final TavernServiceAvailabilityResponseBuilder tavernServiceAvailabilityResponseBuilder;

    @GetMapping("/inn/info")
    public Response exploreInfo(UserEntity userEntity) {
        return tavernServiceAvailabilityResponseBuilder.build(
                TavernServiceAvailabilityResponseBuilderConfiguration.builder()
                        .services(innServiceAvailabilityCalculator.getAvailableServices(userEntity))
                        .userEntity(userEntity)
                        .build()
        );
    }
}
