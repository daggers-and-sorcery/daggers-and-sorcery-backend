package com.swordssorcery.server.model.definition.item;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "itemlist")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemDefinitionList {

    @XmlElement(name = "item")
    private List<ItemDefinition> item;

    public List<ItemDefinition> getItemList() {
        return item;
    }
}
