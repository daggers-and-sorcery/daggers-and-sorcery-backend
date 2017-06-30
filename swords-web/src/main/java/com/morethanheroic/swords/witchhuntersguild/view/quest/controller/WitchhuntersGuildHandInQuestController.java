package com.morethanheroic.swords.witchhuntersguild.view.quest.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.witchhuntersguild.service.WitchhuntersGuildEntityFactory;
import com.morethanheroic.swords.witchhuntersguild.service.WitchhuntersGuildHandInQuestCalculator;
import com.morethanheroic.swords.witchhuntersguild.view.quest.response.service.WitchhuntersGuildQuestResponseBuilder;
import com.morethanheroic.swords.witchhuntersguild.view.quest.response.service.domain.WitchhuntersGuildQuestResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WitchhuntersGuildHandInQuestController {

    private final WitchhuntersGuildHandInQuestCalculator witchhuntersGuildHandInQuestCalculator;
    private final WitchhuntersGuildQuestResponseBuilder witchhuntersGuildQuestResponseBuilder;
    private final WitchhuntersGuildEntityFactory witchhuntersGuildEntityFactory;

    //TODO: Do this a bit more immersively. So the player should get a response if the hand-in was successful.
    @GetMapping("/witchhunters-guild/quest/hand-in")
    public Response handInQuest(final UserEntity userEntity) {
        witchhuntersGuildHandInQuestCalculator.handInQuest(userEntity);

        return witchhuntersGuildQuestResponseBuilder.build(
                WitchhuntersGuildQuestResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .jobDefinition(witchhuntersGuildEntityFactory.getEntity(userEntity).getJob())
                        .build()
        );
    }
}
