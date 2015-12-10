package com.morethanheroic.swords.recipe.service.loader.domain;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class RawRecipeIngredient {

    private int id;
    private int amount;
}
