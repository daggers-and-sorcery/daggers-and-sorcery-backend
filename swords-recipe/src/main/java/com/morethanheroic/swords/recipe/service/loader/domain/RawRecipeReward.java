package com.morethanheroic.swords.recipe.service.loader.domain;

import com.morethanheroic.swords.recipe.domain.RecipeReward;
import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Contains the freshly loaded details of a {@link RecipeReward} domain object.
 */
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class RawRecipeReward {

    private int id;
    private int amount;
}
