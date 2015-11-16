package com.morethanheroic.swords.spell.service.loader;

import com.morethanheroic.swords.definition.service.XMLDefinitionLoader;
import com.morethanheroic.swords.spell.domain.SpellDefinition;
import com.morethanheroic.swords.spell.service.loader.domain.RawSpellDefinition;
import com.morethanheroic.swords.spell.service.transformer.SpellDefinitionTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpellDefinitionLoader {

    @Autowired
    private XMLDefinitionLoader xmlDefinitionLoader;

    @Autowired
    private SpellDefinitionTransformer spellDefinitionTransformer;

    public List<SpellDefinition> loadSpellDefinitions() throws Exception {
        return loadRawSpellDefinitions().stream().map(spellDefinitionTransformer::transform).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    private List<RawSpellDefinition> loadRawSpellDefinitions() throws JAXBException, IOException, SAXException {
        return xmlDefinitionLoader.loadDefinitions(RawSpellDefinition.class, "classpath:data/spell/definition/", "classpath:data/spell/schema.xsd");
    }
}
