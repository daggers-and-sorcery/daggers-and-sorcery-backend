package com.morethanheroic.swords.profile.service.response.skill.domain;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class SkillAttributePartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private UserEntity userEntity;
    private List<SkillType> skills;
}
