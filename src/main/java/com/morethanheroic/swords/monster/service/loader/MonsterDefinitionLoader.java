package com.morethanheroic.swords.monster.service.loader;

import com.morethanheroic.swords.definition.service.XmlDefinitionLoader;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.monster.service.transformer.MonsterDefinitionTransformer;
import com.morethanheroic.swords.monster.service.loader.domain.RawMonsterDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonsterDefinitionLoader {

    @Autowired
    private XmlDefinitionLoader xmlDefinitionLoader;

    @Autowired
    private MonsterDefinitionTransformer monsterDefinitionTransformer;

    public List<MonsterDefinition> loadMonsterDefinitions() throws JAXBException, IOException, SAXException {
        return loadRawMonsterDefinitions().stream().map(monsterDefinitionTransformer::transform).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    private List<RawMonsterDefinition> loadRawMonsterDefinitions() throws JAXBException, IOException, SAXException {
        return xmlDefinitionLoader.loadDefinitions(RawMonsterDefinition.class, "classpath:data/monster/definition/", "classpath:data/monster/schema.xsd");
    }
}
