package com.morethanheroic.swords.ladder.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.ladder.service.LadderService;
import com.morethanheroic.swords.ladder.view.response.domain.configuration.LadderResponseBuilderConfiguration;
import com.morethanheroic.swords.ladder.view.response.service.LadderResponseBuilder;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LadderController {

    private final LadderService ladderService;
    private final LadderResponseBuilder ladderResponseBuilder;

    @GetMapping(value = "/ladder/skill/{skill}/{page}")
    public Response ladderInfo(UserEntity userEntity, @PathVariable SkillType skill, @PathVariable int page) {
        return ladderResponseBuilder.build(
                LadderResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .ladderEntries(ladderService.getLadderData(userEntity, skill, page))
                        .pageCount(ladderService.pageCount())
                        .build()
        );
    }
}
