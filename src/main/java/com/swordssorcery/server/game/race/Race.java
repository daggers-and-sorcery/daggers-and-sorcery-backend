package com.swordssorcery.server.game.race;

import com.swordssorcery.server.game.attribute.Attribute;

import java.util.HashMap;

public enum Race {
    HUMAN, ORC, DWARF, ELF, DARK_ELF, LIZARDMEN, GNOME, DRACONIC;

    static {
        ORC.setRacialModifier(Attribute.STRENGTH, 20);
        ORC.setRacialModifier(Attribute.ENDURANCE, 10);
        ORC.setRacialModifier(Attribute.VITALITY, 10);
        ORC.setRacialModifier(Attribute.INTELLIGENCE, -30);
        ORC.setRacialModifier(Attribute.WISDOM, -10);
        ORC.setRacialModifier(Attribute.WILLPOWER, -10);

        DWARF.setRacialModifier(Attribute.PERCEPTION, 20);
        DWARF.setRacialModifier(Attribute.STRENGTH, 10);
        DWARF.setRacialModifier(Attribute.VITALITY, 10);
        DWARF.setRacialModifier(Attribute.SWIFTNESS, -20);
        DWARF.setRacialModifier(Attribute.DEXTERITY, -10);
        DWARF.setRacialModifier(Attribute.CHARISMA, -10);
        DWARF.setRacialModifier(Attribute.BEAUTY, -10);

        ELF.setRacialModifier(Attribute.INTELLIGENCE, 20);
        ELF.setRacialModifier(Attribute.WILLPOWER, 10);
        ELF.setRacialModifier(Attribute.BEAUTY, 10);
        ELF.setRacialModifier(Attribute.STRENGTH, -20);
        ELF.setRacialModifier(Attribute.ENDURANCE, -20);
        ELF.setRacialModifier(Attribute.VITALITY, -10);

        DARK_ELF.setRacialModifier(Attribute.DEXTERITY, 20);
        DARK_ELF.setRacialModifier(Attribute.SWIFTNESS, 10);
        DARK_ELF.setRacialModifier(Attribute.PERCEPTION, 10);
        DARK_ELF.setRacialModifier(Attribute.STRENGTH, -20);
        DARK_ELF.setRacialModifier(Attribute.WISDOM, -20);
        DARK_ELF.setRacialModifier(Attribute.CHARISMA, -10);

        LIZARDMEN.setRacialModifier(Attribute.DEXTERITY, 30);
        LIZARDMEN.setRacialModifier(Attribute.PERCEPTION, 10);
        LIZARDMEN.setRacialModifier(Attribute.STRENGTH, -30);
        LIZARDMEN.setRacialModifier(Attribute.INTELLIGENCE, -20);
        LIZARDMEN.setRacialModifier(Attribute.VITALITY, -10);

        GNOME.setRacialModifier(Attribute.INTELLIGENCE, 30);
        GNOME.setRacialModifier(Attribute.WILLPOWER, 10);
        GNOME.setRacialModifier(Attribute.STRENGTH, -30);
        GNOME.setRacialModifier(Attribute.VITALITY, -20);
        GNOME.setRacialModifier(Attribute.DEXTERITY, -10);

        DRACONIC.setRacialModifier(Attribute.WILLPOWER, 30);
        DRACONIC.setRacialModifier(Attribute.ENDURANCE, 10);
        DRACONIC.setRacialModifier(Attribute.DEXTERITY, -30);
        DRACONIC.setRacialModifier(Attribute.SWIFTNESS, -20);
        DRACONIC.setRacialModifier(Attribute.PERCEPTION, -10);
    }

    public static final int NO_RACIAL_MODIFIER = 0;

    private final HashMap<Attribute, Integer> racialAttributeModifiers = new HashMap<>();

    private void setRacialModifier(Attribute attribute, int value) {
        racialAttributeModifiers.put(attribute, value);
    }

    public int getRacialModifier(Attribute attribute) {
        return racialAttributeModifiers.containsKey(attribute) ? racialAttributeModifiers.get(attribute) : NO_RACIAL_MODIFIER;
    }
}
