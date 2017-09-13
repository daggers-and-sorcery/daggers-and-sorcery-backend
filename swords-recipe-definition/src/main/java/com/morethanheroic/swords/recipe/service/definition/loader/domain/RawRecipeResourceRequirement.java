package com.morethanheroic.swords.recipe.service.definition.loader.domain;

import com.morethanheroic.swords.recipe.domain.RecipeResource;
import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class RawRecipeResourceRequirement implements RawRecipeRequirement {

    private RecipeResource resource;
    private int amount;
}
