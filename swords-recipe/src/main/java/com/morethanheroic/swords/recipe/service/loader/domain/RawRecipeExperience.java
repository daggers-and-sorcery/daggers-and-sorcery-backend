package com.morethanheroic.swords.recipe.service.loader.domain;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class RawRecipeExperience {

    //TODO: use a real skill type here instead of string.
    private String skill;
    private int amount;
}
