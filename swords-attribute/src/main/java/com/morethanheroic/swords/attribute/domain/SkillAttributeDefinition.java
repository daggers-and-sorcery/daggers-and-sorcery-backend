package com.morethanheroic.swords.attribute.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class SkillAttributeDefinition {

    private String name;
    private SkillAttribute skillAttribute;
    private GeneralAttribute incrementedAttribute;
}
