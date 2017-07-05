package com.morethanheroic.swords.witchhuntersguild.view.quest.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.witchhuntersguild.service.WitchhuntersGuildEntityFactory;
import com.morethanheroic.swords.witchhuntersguild.view.quest.response.service.WitchhuntersGuildQuestResponseBuilder;
import com.morethanheroic.swords.witchhuntersguild.view.quest.response.service.domain.WitchhuntersGuildQuestResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WitchhuntersGuildQuestController {

    private final WitchhuntersGuildQuestResponseBuilder witchhuntersGuildQuestResponseBuilder;
    private final WitchhuntersGuildEntityFactory witchhuntersGuildEntityFactory;

    @GetMapping("/witchhunters-guild/quest")
    public Response showQuest(final UserEntity userEntity) {
        return witchhuntersGuildQuestResponseBuilder.build(
                WitchhuntersGuildQuestResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .jobDefinition(witchhuntersGuildEntityFactory.getEntity(userEntity).getJob())
                        .build()
        );
    }
}
