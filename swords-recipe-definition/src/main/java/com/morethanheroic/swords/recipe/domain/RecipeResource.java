package com.morethanheroic.swords.recipe.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RecipeResource {

    MANA("Mana points");

    private final String name;
}
