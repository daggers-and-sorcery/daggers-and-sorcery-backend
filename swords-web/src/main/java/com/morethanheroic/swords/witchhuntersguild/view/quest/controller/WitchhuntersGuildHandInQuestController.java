package com.morethanheroic.swords.witchhuntersguild.view.quest.controller;

import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.witchhuntersguild.service.WitchhuntersGuildHandInQuestCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WitchhuntersGuildHandInQuestController {

    private final WitchhuntersGuildHandInQuestCalculator witchhuntersGuildHandInQuestCalculator;

    //TODO: Response
    @GetMapping("/witchhunters-guild/quest/hand-in")
    public boolean handInQuest(final UserEntity userEntity) {
        return witchhuntersGuildHandInQuestCalculator.handInQuest(userEntity);
    }
}
