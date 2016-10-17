package com.morethanheroic.swords.skill.fletching.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.fletching.view.response.domain.FletchingInfoResponseBuilderConfigration;
import com.morethanheroic.swords.skill.fletching.view.response.service.FletchingInfoResponseBuilder;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FletchingInfoController {

    private final FletchingInfoResponseBuilder fletchingInfoResponseBuilder;
    private final SkillEntityFactory skillEntityFactory;

    @GetMapping("/skill/fletching/info")
    public Response smeltingInfo(UserEntity userEntity) {
        return fletchingInfoResponseBuilder.build(
                FletchingInfoResponseBuilderConfigration.builder()
                        .userEntity(userEntity)
                        .fletchingLevel(skillEntityFactory.getEntity(userEntity.getId()).getLevel(SkillType.FLETCHING))
                        .build()
        );
    }
}
