package com.morethanheroic.swords.attribute.service.loader.entity;

import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "skill-attribute")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
public class RawSkillAttributeDefinition {

    @XmlElement
    private String name;

    @XmlElement(name = "incremented-attribute")
    private GeneralAttribute incrementedAttribute;

    @XmlElement(name = "has-page")
    private boolean hasPage;
}
