package com.morethanheroic.swords.skill.smithing.view.controller.smelting;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.skill.smithing.view.response.domain.configuration.smelting.SmeltingInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.smithing.view.response.service.smelting.SmeltingInfoResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmeltingInfoController {

    @Autowired
    private SmeltingInfoResponseBuilder smeltingInfoResponseBuilder;

    @RequestMapping(value = "/skill/smithing/smelting/info", method = RequestMethod.GET)
    public Response smeltingInfo(UserEntity userEntity) {
        return smeltingInfoResponseBuilder.build(
                SmeltingInfoResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .build()
        );
    }
}
