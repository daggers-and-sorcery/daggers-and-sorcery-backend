package com.morethanheroic.swords.skill.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.skill.view.response.domain.configuration.SkillListResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.view.response.service.SkillListResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SkillListController {

    @Autowired
    private SkillListResponseBuilder skillListResponseBuilder;

    @RequestMapping(value = "/skill/list", method = RequestMethod.GET)
    public Response skillList(UserEntity userEntity) {
        return skillListResponseBuilder.build(
                SkillListResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .build()
        );
    }
}
