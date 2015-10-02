package com.morethanheroic.swords.spell.service.domain;

public class SpellDefinition {

    private final int id;

    public SpellDefinition(RawSpellDefinition rawSpellDefinition) {
        this.id = rawSpellDefinition.getId();
    }

    public int getId() {
        return id;
    }
}
