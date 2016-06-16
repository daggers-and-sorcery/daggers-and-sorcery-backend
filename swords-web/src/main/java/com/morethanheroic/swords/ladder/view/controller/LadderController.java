package com.morethanheroic.swords.ladder.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.ladder.service.LadderService;
import com.morethanheroic.swords.ladder.view.response.domain.configuration.LadderResponseBuilderConfiguration;
import com.morethanheroic.swords.ladder.view.response.service.LadderResponseBuilder;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LadderController {

    @Autowired
    private LadderService ladderService;

    @Autowired
    private LadderResponseBuilder ladderResponseBuilder;

    @RequestMapping(value = "/ladder/skill/{skill}", method = RequestMethod.GET)
    public Response ladderInfo(UserEntity userEntity, @PathVariable SkillType skill) {
        return ladderResponseBuilder.build(
                LadderResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .ladderEntries(ladderService.getLadderData(userEntity, skill))
                        .build()
        );
    }
}
