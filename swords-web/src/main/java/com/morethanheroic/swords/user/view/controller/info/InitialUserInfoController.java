package com.morethanheroic.swords.user.view.controller.info;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.view.response.service.info.initial.InitialUserInfoResponseBuilder;
import com.morethanheroic.swords.user.view.response.service.info.initial.domain.configuration.InitialUserInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.witchhuntersguild.service.WitchhuntersGuildCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Return an initial information after the user's login.
 */
@RestController
@RequiredArgsConstructor
public class InitialUserInfoController {

    private final InitialUserInfoResponseBuilder initialUserInfoResponseBuilder;
    private final WitchhuntersGuildCalculator witchhuntersGuildCalculator;

    @GetMapping("/user/info")
    public Response initialUserInformation(final UserEntity userEntity) {
        return initialUserInfoResponseBuilder.build(
                InitialUserInfoResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .witchhuntersGuildUnlocked(witchhuntersGuildCalculator.isWitchhuntersGuildUnlocked(userEntity))
                        .build()
        );
    }
}
