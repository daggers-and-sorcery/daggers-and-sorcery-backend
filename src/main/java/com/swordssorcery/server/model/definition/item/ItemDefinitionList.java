package com.swordssorcery.server.model.definition.item;

import com.swordssorcery.server.definition.loader.DefinitionList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "itemlist")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemDefinitionList extends DefinitionList<ItemDefinition> {

    @XmlElement(name = "item")
    private List<ItemDefinition> item;

    @Override
    public List<ItemDefinition> getDefinitionList() {
        return item;
    }
}
