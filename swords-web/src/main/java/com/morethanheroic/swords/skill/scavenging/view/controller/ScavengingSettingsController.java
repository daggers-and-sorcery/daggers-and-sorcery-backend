package com.morethanheroic.swords.skill.scavenging.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.scavenging.domain.ScavengingEntity;
import com.morethanheroic.swords.scavenging.service.ScavengingEntityFactory;
import com.morethanheroic.swords.skill.scavenging.view.request.domain.ChangeScavengingSettingsRequest;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScavengingSettingsController {

    private final ScavengingEntityFactory scavengingEntityFactory;
    private final ResponseFactory responseFactory;

    @PostMapping("/skill/scavenging/settings")
    public Response changeScavengingSettings(final UserEntity userEntity, @RequestBody ChangeScavengingSettingsRequest request) {
        final ScavengingEntity scavengingEntity = scavengingEntityFactory.getEntity(userEntity);

        scavengingEntity.setScavengingEnabled(request.isScavengingEnabled());

        return responseFactory.successfulResponse(userEntity);
    }
}
