package com.morethanheroic.swords.explore.service.event.loader.domain;

import com.morethanheroic.swords.explore.service.event.ExplorationEventLocation;
import com.morethanheroic.swords.explore.service.event.ExplorationEventRarity;
import com.morethanheroic.swords.explore.service.event.ExplorationEventTerrain;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A freshly loaded event definition data from the event's xml file.
 */
@Data
@XmlRootElement(name = "exploration-event")
@XmlAccessorType(XmlAccessType.FIELD)
public class RawEventDefinition {

    private int id;
    private ExplorationEventLocation location;
    private ExplorationEventTerrain terrain;
    private ExplorationEventRarity rarity;
}
