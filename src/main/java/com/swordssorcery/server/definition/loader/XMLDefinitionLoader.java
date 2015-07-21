package com.swordssorcery.server.definition.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.util.List;

@Service
public class XMLDefinitionLoader {

    @Autowired
    private ApplicationContext applicationContext;

    public List loadDefinitions(Class<? extends DefinitionList> clazz, String resourcePath) throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        return ((DefinitionList) jaxbUnmarshaller.unmarshal(applicationContext.getResource(resourcePath).getFile())).getDefinitionList();
    }
}
