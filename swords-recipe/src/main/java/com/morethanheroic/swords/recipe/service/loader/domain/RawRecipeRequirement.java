package com.morethanheroic.swords.recipe.service.loader.domain;

import com.morethanheroic.swords.recipe.domain.RecipeRequirement;
import com.morethanheroic.swords.skill.SkillType;
import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Contains the freshly loaded details of a {@link RecipeRequirement} domain object.
 */
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class RawRecipeRequirement {

    private SkillType skill;
    private int amount;
}
