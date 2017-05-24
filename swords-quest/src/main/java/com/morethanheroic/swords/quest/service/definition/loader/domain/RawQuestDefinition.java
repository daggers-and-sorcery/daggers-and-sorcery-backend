package com.morethanheroic.swords.quest.service.definition.loader.domain;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@XmlRootElement(name = "quest")
@XmlAccessorType(XmlAccessType.FIELD)
public class RawQuestDefinition {

    private int id;
    private String name;
    private String description;

    @XmlElement(name = "completed-at-stage")
    private int completedAtStage;
}
