package com.morethanheroic.swords.shop.service.definition.loader.domain;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class RawAvailableFeatures {

    private boolean buying;
    private boolean selling;
}
