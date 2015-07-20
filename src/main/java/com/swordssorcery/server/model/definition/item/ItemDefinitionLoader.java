package com.swordssorcery.server.model.definition.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.util.List;

@Service
public class ItemDefinitionLoader {

    @Autowired
    private ApplicationContext applicationContext;

    public List<ItemDefinition> loadItemDefinitionsAsList() throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ItemDefinitionList.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        return ((ItemDefinitionList) jaxbUnmarshaller.unmarshal(applicationContext.getResource("classpath:data/itemlist.xml").getFile())).getItemList();
    }
}
