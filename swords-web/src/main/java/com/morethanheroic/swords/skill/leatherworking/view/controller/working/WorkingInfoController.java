package com.morethanheroic.swords.skill.leatherworking.view.controller.working;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.working.WorkingInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.leatherworking.view.response.service.working.WorkingInfoResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkingInfoController {

    @Autowired
    private WorkingInfoResponseBuilder workingInfoResponseBuilder;

    @RequestMapping(value = "/skill/leatherworking/working/info", method = RequestMethod.GET)
    public Response workingInfo(UserEntity userEntity) {
        return workingInfoResponseBuilder.build(
                WorkingInfoResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .build()
        );
    }
}
