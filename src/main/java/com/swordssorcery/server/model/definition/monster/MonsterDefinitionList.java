package com.swordssorcery.server.model.definition.monster;

import com.swordssorcery.server.loader.definition.DefinitionList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "monsterlist")
@XmlAccessorType(XmlAccessType.FIELD)
public class MonsterDefinitionList extends DefinitionList<MonsterDefinition> {

    @XmlElement(name = "monster")
    private List<MonsterDefinition> monster;

    @Override
    public List<MonsterDefinition> getDefinitionList() {
        return monster;
    }
}
