package com.morethanheroic.swords.recipe.service.loader.domain;

import com.morethanheroic.swords.recipe.domain.RecipeIngredient;
import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Contains the freshly loaded details of a {@link RecipeIngredient} domain object.
 */
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class RawRecipeIngredient {

    private int id;
    private int amount;
}
