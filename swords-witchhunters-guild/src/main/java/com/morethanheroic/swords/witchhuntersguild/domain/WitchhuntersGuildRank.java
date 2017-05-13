package com.morethanheroic.swords.witchhuntersguild.domain;

/**
 * Shows the rank of the user in the witchhunter's guild.
 */
public enum WitchhuntersGuildRank {

    NEWCOMER("Newcomer"),
    NOVICE("Novice"),
    APPRENTICE("Apprentice"),
    ADEPT("Adept");

    private final String name;

    WitchhuntersGuildRank(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
