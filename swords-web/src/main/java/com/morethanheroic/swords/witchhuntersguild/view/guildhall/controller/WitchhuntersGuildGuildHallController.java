package com.morethanheroic.swords.witchhuntersguild.view.guildhall.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.witchhuntersguild.domain.WitchhuntersGuildEntity;
import com.morethanheroic.swords.witchhuntersguild.service.WitchhuntersGuildEntityFactory;
import com.morethanheroic.swords.witchhuntersguild.view.guildhall.response.service.WitchhuntersGuildGuildHallResponseBuilder;
import com.morethanheroic.swords.witchhuntersguild.view.guildhall.response.service.domain.WitchhuntersGuildGuildHallResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the Guild hall function of the Witchhunter's guild.
 */
@RestController
@RequiredArgsConstructor
public class WitchhuntersGuildGuildHallController {

    private final WitchhuntersGuildEntityFactory witchhuntersGuildEntityFactory;
    private final WitchhuntersGuildGuildHallResponseBuilder witchhuntersGuildGuildHallResponseBuilder;

    @GetMapping("/witchhunters-guild/guild-hall")
    public Response showGuildHall(final UserEntity userEntity) {
        final WitchhuntersGuildEntity witchhuntersGuildEntity = witchhuntersGuildEntityFactory.getEntity(userEntity);

        return witchhuntersGuildGuildHallResponseBuilder.build(
                WitchhuntersGuildGuildHallResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .witchhuntersGuildEntity(witchhuntersGuildEntity)
                        .build()
        );
    }
}
