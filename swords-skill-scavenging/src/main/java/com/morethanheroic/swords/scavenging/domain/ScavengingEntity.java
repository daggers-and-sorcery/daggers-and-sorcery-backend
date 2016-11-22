package com.morethanheroic.swords.scavenging.domain;

import com.morethanheroic.entity.domain.Entity;
import com.morethanheroic.swords.metadata.domain.NumericMetadataEntity;
import com.morethanheroic.swords.metadata.domain.TextMetadataEntity;

/**
 * Contains all scavenging related info.
 */
public class ScavengingEntity implements Entity {

    private final int id;
    private final NumericMetadataEntity scavengingPoints;
    private final TextMetadataEntity scavengingEnabled;

    public ScavengingEntity(final int id, final NumericMetadataEntity scavengingPoints, final TextMetadataEntity scavengingEnabled) {
        this.id = id;
        this.scavengingPoints = scavengingPoints;
        this.scavengingEnabled = scavengingEnabled;
    }

    @Override
    public int getId() {
        return id;
    }

    public boolean isScavengingEnabled() {
        return scavengingEnabled.getValue().equals("ENABLED");
    }

    public void setScavengingEnabled(final boolean enabled) {
        scavengingEnabled.setValue(enabled ? "ENABLED" : "DISABLED");
    }

    public int getScavengingPoint() {
        return scavengingPoints.getValue();
    }

    public void setScavengingPoint(int value) {
        scavengingPoints.setValue(value);
    }
}
