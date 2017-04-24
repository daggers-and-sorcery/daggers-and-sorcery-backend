package com.morethanheroic.swords.skill.smithing.view.controller.smelting;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.skill.smithing.view.response.domain.configuration.smelting.SmeltingInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.smithing.view.response.service.smelting.SmeltingInfoResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SmeltingInfoController {

    private final SmeltingInfoResponseBuilder smeltingInfoResponseBuilder;
    private final SkillEntityFactory skillEntityFactory;

    @GetMapping("/skill/smithing/smelting/info")
    public Response smeltingInfo(UserEntity userEntity) {
        return smeltingInfoResponseBuilder.build(
                SmeltingInfoResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .smithingLevel(skillEntityFactory.getSkillEntity(userEntity).getLevel(SkillType.SMITHING))
                        .build()
        );
    }
}
