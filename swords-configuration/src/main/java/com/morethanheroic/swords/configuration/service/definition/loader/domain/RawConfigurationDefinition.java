package com.morethanheroic.swords.configuration.service.definition.loader.domain;

import com.morethanheroic.swords.configuration.domain.Configuration;
import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@XmlRootElement(name = "configuration")
@XmlAccessorType(XmlAccessType.FIELD)
public class RawConfigurationDefinition {

    private Configuration id;
    private int value;
}
