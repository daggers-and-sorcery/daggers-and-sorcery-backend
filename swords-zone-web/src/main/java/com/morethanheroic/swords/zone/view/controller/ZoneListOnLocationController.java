package com.morethanheroic.swords.zone.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.location.domain.Location;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.zone.view.response.service.ZoneListOnLocationResponseBuilder;
import com.morethanheroic.swords.zone.view.response.service.domain.ZoneListOnLocationResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ZoneListOnLocationController {

    private final ZoneListOnLocationResponseBuilder zoneListOnLocationResponseBuilder;

    @GetMapping("/zone/list/{location}")
    public Response listZonesOnLocation(final UserEntity userEntity, final @PathVariable Location location) {
        return zoneListOnLocationResponseBuilder.build(
                ZoneListOnLocationResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .location(location)
                        .build()
        );
    }
}
