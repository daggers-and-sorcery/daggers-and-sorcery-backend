package com.morethanheroic.swords.metadata.service.loader.domain;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@Getter
public class RawMetadataValueDefinition {

    private int id;
    private String value;
}