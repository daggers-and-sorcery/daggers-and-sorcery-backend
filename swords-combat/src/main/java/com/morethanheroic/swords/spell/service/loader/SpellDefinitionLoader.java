package com.morethanheroic.swords.spell.service.loader;

import com.morethanheroic.swords.spell.domain.SpellDefinition;
import com.morethanheroic.swords.spell.service.loader.domain.RawSpellDefinition;
import com.morethanheroic.swords.spell.service.transformer.SpellDefinitionTransformer;
import com.morethanheroic.xml.service.loader.NumericXmlDefinitionLoader;
import com.morethanheroic.xml.service.loader.domain.NumericDefinitionLoadingContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpellDefinitionLoader {

    private final NumericXmlDefinitionLoader numericXmlDefinitionLoader;
    private final SpellDefinitionTransformer spellDefinitionTransformer;

    public List<SpellDefinition> loadSpellDefinitions() throws JAXBException, IOException, SAXException {
        return loadRawSpellDefinitions().stream().map(spellDefinitionTransformer::transform).collect(Collectors.toList());
    }

    private List<RawSpellDefinition> loadRawSpellDefinitions() throws JAXBException, IOException, SAXException {
        return numericXmlDefinitionLoader.loadDefinitions(
                NumericDefinitionLoadingContext.<RawSpellDefinition>builder()
                        .clazz(RawSpellDefinition.class)
                        .resourcePath("classpath:data/spell/definition/")
                        .schemaPath("classpath:data/spell/schema.xsd")
                        .build()
        );
    }
}
