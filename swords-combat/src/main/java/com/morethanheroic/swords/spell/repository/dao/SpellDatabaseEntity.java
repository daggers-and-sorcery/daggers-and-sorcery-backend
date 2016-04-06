package com.morethanheroic.swords.spell.repository.dao;

public class SpellDatabaseEntity {

    private int userId;
    private int spellId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSpellId() {
        return spellId;
    }

    public void setSpellId(int spellId) {
        this.spellId = spellId;
    }
}
