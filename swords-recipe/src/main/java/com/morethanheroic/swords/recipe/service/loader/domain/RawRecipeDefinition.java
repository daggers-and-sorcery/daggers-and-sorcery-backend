package com.morethanheroic.swords.recipe.service.loader.domain;

import com.morethanheroic.swords.recipe.domain.RecipeType;
import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class RawRecipeDefinition {

    private int id;

    private int chance;

    @XmlElement(name = "type")
    private RecipeType type;

    @XmlElementWrapper(name = "ingredient-list")
    @XmlElement(name = "ingredient")
    private List<RawRecipeIngredient> recipeIngredientList;

    @XmlElementWrapper(name = "reward-list")
    @XmlElement(name = "reward")
    private List<RawRecipeReward> rawRecipeRewardList;

    @XmlElementWrapper(name = "experience-list")
    @XmlElement(name = "experience")
    private List<RawRecipeExperience> rawRecipeExperienceList;
}
