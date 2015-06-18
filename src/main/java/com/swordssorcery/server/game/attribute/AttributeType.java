package com.swordssorcery.server.game.attribute;

public enum AttributeType {
    BASIC("basic"), COMBAT("combat"), GENERAL_MENTAL("general_mental"), GENERAL_PHYSICAL("general_physical");

    private final String shortName;

    AttributeType(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }
}
