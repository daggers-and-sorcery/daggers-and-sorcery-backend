package com.morethanheroic.swords.witchhuntersguild.view.info.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.witchhuntersguild.service.WitchhuntersGuildAccessibilityCalculator;
import com.morethanheroic.swords.witchhuntersguild.view.info.response.service.WitchhuntersGuildInfoResponseBuilder;
import com.morethanheroic.swords.witchhuntersguild.view.info.response.service.domain.WitchhuntersGuildInfoResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WitchhuntersGuildInfoController {

    private final WitchhuntersGuildInfoResponseBuilder witchhuntersGuildInfoResponseBuilder;
    private final WitchhuntersGuildAccessibilityCalculator witchhuntersGuildAccessibilityCalculator;

    @GetMapping("/witchhunters-guild/info")
    public Response witchhuntesGuildInfo(final UserEntity userEntity) {
        return witchhuntersGuildInfoResponseBuilder.build(
                WitchhuntersGuildInfoResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .accessible(witchhuntersGuildAccessibilityCalculator.hasAccess(userEntity))
                        .build()
        );
    }
}
