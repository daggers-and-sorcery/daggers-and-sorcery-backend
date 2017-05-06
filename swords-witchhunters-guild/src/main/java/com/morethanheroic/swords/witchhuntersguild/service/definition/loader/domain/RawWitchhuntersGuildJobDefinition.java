package com.morethanheroic.swords.witchhuntersguild.service.definition.loader.domain;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@XmlRootElement(name = "witchhunters-guild-job")
@XmlAccessorType(XmlAccessType.FIELD)
public class RawWitchhuntersGuildJobDefinition {

    private int id;
    private String name;
    private String description;

    @XmlElementWrapper(name = "requirements")
    @XmlElement(name = "requirement")
    private List<RawWitchhuntersGuildJobRequirement> requirements;
}
