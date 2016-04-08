package com.morethanheroic.swords.metadata.service.loader.domain;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@XmlRootElement(name = "metadata")
@XmlAccessorType(XmlAccessType.FIELD)
public class RawMetadataDefinition {

    private int id;
    private String name;
    private List<RawMetadataValueDefinition> values;
}
