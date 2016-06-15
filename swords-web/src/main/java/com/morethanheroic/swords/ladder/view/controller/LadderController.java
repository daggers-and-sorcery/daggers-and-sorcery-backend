package com.morethanheroic.swords.ladder.view.controller;

import com.morethanheroic.swords.ladder.domain.LadderEntry;
import com.morethanheroic.swords.ladder.service.LadderService;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LadderController {

    @Autowired
    private LadderService ladderService;

    //TODO: USE RESPONSE!!!
    @RequestMapping(value = "/ladder/skill/{skill}", method = RequestMethod.GET)
    public List<LadderEntry> ladderInfo(UserEntity userEntity, @PathVariable SkillType skill) {
        //TODO: validate skill
        return ladderService.getLadderData(userEntity, skill);
    }
}
