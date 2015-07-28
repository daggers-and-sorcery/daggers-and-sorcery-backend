package com.morethanheroic.swords.map.service.loader;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.morethanheroic.swords.map.service.loader.domain.RawMap;
import com.morethanheroic.swords.map.service.domain.MapDefinition;
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

    public boolean isMapExists(int id) {
        return applicationContext.getResource("classpath:map/" + id + ".json").exists();
    }

    public MapDefinition loadMapDefinition(int id) throws IOException {
        if (!isMapExists(id)) {
            return null;
        }

        Resource resource = applicationContext.getResource("classpath:map/" + id + ".json");
        RawMap rawMap = objectMapper.readValue(resource.getFile(), RawMap.class);

        return mapConverter.convertRawMapToDefinition(rawMap);
    }
}
