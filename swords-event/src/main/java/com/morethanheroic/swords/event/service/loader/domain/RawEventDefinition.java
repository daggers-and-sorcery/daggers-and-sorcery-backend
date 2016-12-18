package com.morethanheroic.swords.event.service.loader.domain;

import com.morethanheroic.swords.event.domain.EventType;
import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A freshly loaded event definition data from the item's xml file.
 */
@Getter
@XmlRootElement(name = "event")
@XmlAccessorType(XmlAccessType.FIELD)
public class RawEventDefinition {

    private int id;
    private String name;
    private EventType type;
    private int length;
}
