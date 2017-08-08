package com.morethanheroic.swords.recipe.service.definition.loader.domain;

import com.morethanheroic.swords.recipe.domain.RecipeRequirement;
import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Contains the freshly loaded details of a {@link RecipeRequirement} domain object.
 */
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class RawRecipeItemRequirement implements RawRecipeRequirement {

    private int id;
    private int amount;
}
