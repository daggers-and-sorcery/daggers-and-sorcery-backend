package com.morethanheroic.swords.skill.smithing.view.controller.working;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.skill.smithing.view.response.domain.configuration.working.SmithingInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.smithing.view.response.service.working.SmithingInfoResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmithingInfoController {

    @Autowired
    private SmithingInfoResponseBuilder smithingInfoResponseBuilder;

    @Autowired
    private SkillEntityFactory skillEntityFactory;

    @RequestMapping(value = "/skill/smithing/working/info", method = RequestMethod.GET)
    public Response smeltingInfo(UserEntity userEntity) {
        return smithingInfoResponseBuilder.build(
                SmithingInfoResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .smithingLevel(skillEntityFactory.getSkillEntity(userEntity).getLevel(SkillType.SMITHING))
                        .build()
        );
    }
}
