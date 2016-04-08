package com.morethanheroic.swords.metadata.domain;

import lombok.Builder;
import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@Builder
@Getter
public class MetadataDefinition {

    private int id;
    private String name;

    @XmlElementWrapper(name = "values")
    @XmlElement(name = "value")
    private List<MetadataValueDefinition> values;
}
