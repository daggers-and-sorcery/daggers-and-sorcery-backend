package com.swordssorcery.server.model.definition.map.loader;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swordssorcery.server.model.definition.map.MapDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MapLoader {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private MapConverter mapConverter;

    private ObjectMapper objectMapper = new ObjectMapper();

    public MapLoader() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public boolean isMapExists(String name) {
        return applicationContext.getResource("classpath:map/" + name + ".json").exists();
    }

    public MapDefinition loadMapDefinition(String name) throws IOException {
        if (!isMapExists(name)) {
            return null;
        }

        Resource resource = applicationContext.getResource("classpath:map/" + name + ".json");
        RawMap rawMap = objectMapper.readValue(resource.getFile(), RawMap.class);

        return mapConverter.convertRawMapToDefinition(rawMap);
    }
}
