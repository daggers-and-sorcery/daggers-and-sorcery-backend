package com.swordssorcery.server.loader.definition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class XMLDefinitionLoader {

    @Autowired
    private ApplicationContext applicationContext;

    public List loadDefinitions(Class clazz, String resourcePath) throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        File folder = applicationContext.getResource(resourcePath).getFile();

        ArrayList list = new ArrayList();

        File[] files = folder.listFiles();
        for (File file : files) {
            list.add(jaxbUnmarshaller.unmarshal(file));
        }

        return list;
    }
}
