package com.morethanheroic.swords.quest.service.definition.loader.domain;

import lombok.Getter;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@XmlRootElement(name = "quest")
@XmlAccessorType(XmlAccessType.FIELD)
public class RawQuestDefinition {

    private int id;
    private String name;
    private String description;

    @XmlElementWrapper(name = "quest-states")
    @XmlElement(name = "quest-state")
    private List<RawQuestStateDefinition> questStates;
}
