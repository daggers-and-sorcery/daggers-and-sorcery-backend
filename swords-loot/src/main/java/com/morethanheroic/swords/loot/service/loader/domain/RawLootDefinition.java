package com.morethanheroic.swords.loot.service.loader.domain;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@XmlRootElement(name = "loot")
@XmlAccessorType(XmlAccessType.FIELD)
public class RawLootDefinition {

    private List<RawDropDefinition> droplist;
}
