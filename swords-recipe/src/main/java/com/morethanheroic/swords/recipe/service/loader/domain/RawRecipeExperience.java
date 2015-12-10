package com.morethanheroic.swords.recipe.service.loader.domain;

import com.morethanheroic.swords.skill.SkillType;
import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class RawRecipeExperience {

    private SkillType skill;
    private int amount;
}
