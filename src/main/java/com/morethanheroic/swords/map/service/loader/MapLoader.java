package com.morethanheroic.swords.map.service.loader;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.morethanheroic.swords.map.service.domain.MapDefinition;
import com.morethanheroic.swords.map.service.loader.domain.RawMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

    public List<MapDefinition> loadMapDefinitions() throws IOException {
        ArrayList<MapDefinition> mapDefinitions = new ArrayList<>();

        for (int i = 1; i < 100; i++) {
            Resource resource = applicationContext.getResource("classpath:map/" + i + ".json");

            if (!resource.exists()) {
                return mapDefinitions;
            }

            mapDefinitions.add(loadMapDefinition(resource.getInputStream(), i));
        }

        throw new IllegalStateException("Should be here! There is more items to read than the actual maxvalue!");
    }

    public MapDefinition loadMapDefinition(InputStream map, int id) throws IOException {
        return mapConverter.convertRawMapToDefinition(id, objectMapper.readValue(map, RawMap.class));
    }
}
