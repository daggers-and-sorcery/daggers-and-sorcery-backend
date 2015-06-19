package com.swordssorcery.server.game.race;

import com.swordssorcery.server.game.attribute.Attribute;

import java.util.HashMap;

public enum Race {
    HUMAN, ORC, DWARF, ELF, DARK_ELF, LIZARDMEN, GNOME, DRACONIC;

    static {
        //ORC.setRacialModifier(Attribute.STRENGTH, 2);
        //TODO: fill this
    }

    private static final int NO_RACIAL_MODIFIER = 0;

    private final HashMap<Attribute, Integer> racialAttributeModifiers = new HashMap<>();

    private void setRacialModifier(Attribute attribute, int value) {
        racialAttributeModifiers.put(attribute,value);
    }

    public int getRacialModifier(Attribute attribute) {
        return racialAttributeModifiers.containsKey(attribute) ? racialAttributeModifiers.get(attribute) : NO_RACIAL_MODIFIER;
    }
}
