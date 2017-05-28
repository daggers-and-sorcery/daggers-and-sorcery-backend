package com.morethanheroic.swords.quest.service.definition.loader.domain;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class RawQuestStateDefinition {

    private String description;

    @XmlElement(name = "event-id")
    private int eventId;

    @XmlElement(name = "event-stage")
    private int eventStage;
}
