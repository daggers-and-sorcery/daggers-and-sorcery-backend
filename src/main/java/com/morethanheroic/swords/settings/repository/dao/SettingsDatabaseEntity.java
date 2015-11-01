package com.morethanheroic.swords.settings.repository.dao;

public class SettingsDatabaseEntity {

    private int userId;
    private boolean scavengingEnabled;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isScavengingEnabled() {
        return scavengingEnabled;
    }

    public void setScavengingEnabled(boolean scavengingEnabled) {
        this.scavengingEnabled = scavengingEnabled;
    }
}
