package com.morethanheroic.swords.witchhuntersguild.service.definition.loader.domain;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Contains the raw data of a witchhunter's guild job.
 */
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class RawWitchhuntersGuildJobRequirement {

    private int id;
    private int amount;

    @XmlAttribute
    private WitchhuntersGuildJobRequirementType type;
}
