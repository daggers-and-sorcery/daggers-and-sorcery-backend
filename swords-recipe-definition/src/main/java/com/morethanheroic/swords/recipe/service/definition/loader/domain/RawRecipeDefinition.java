package com.morethanheroic.swords.recipe.service.definition.loader.domain;

import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeType;
import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Contains the freshly loaded details of a {@link RecipeDefinition} domain object.
 */
@Getter
@XmlRootElement(name = "recipe")
@XmlAccessorType(XmlAccessType.FIELD)
public class RawRecipeDefinition {

    private int id;

    private int chance;

    private String name;

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

    @XmlElements({
            @XmlElement(name = "skill-requirement", type = RawRecipeSkillRequirement.class),
            @XmlElement(name = "item-requirement", type = RawRecipeItemRequirement.class),
            @XmlElement(name = "resource-requirement", type = RawRecipeResourceRequirement.class)
    })
    @XmlElementWrapper(name = "requirement-list")
    private List<RawRecipeRequirement> rawRecipeRequirementList;
}
