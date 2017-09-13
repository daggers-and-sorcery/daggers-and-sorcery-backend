package com.morethanheroic.swords.zone.service.definition.loader.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.morethanheroic.swords.location.domain.Location;
import lombok.Data;

@Data
@XmlRootElement(name = "zone")
@XmlAccessorType(XmlAccessType.FIELD)
public class RawZoneDefinition {

    private String id;
    private String name;
    private Location location;
    private boolean enabled;
    private String description;

    @XmlElement(name = "minimum-level")
    private int minimumLevel;

    @XmlElement(name = "maximum-level")
    private int maximumLevel;
}
