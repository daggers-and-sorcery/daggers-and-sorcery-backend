package com.morethanheroic.swords.map.service.loader;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.morethanheroic.swords.map.service.domain.MapDefinition;
import com.morethanheroic.swords.map.service.loader.domain.RawMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
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

    public boolean isMapExists(int id) {
        return applicationContext.getResource("classpath:map/" + id + ".json").exists();
    }

    public List<MapDefinition> loadMapDefinitions() throws IOException {
        File[] maps = applicationContext.getResource("classpath:map").getFile().listFiles();

        ArrayList<MapDefinition> mapDefinitions = new ArrayList<>();

        for (File map : maps) {
            mapDefinitions.add(loadMapDefinition(map));
        }

        return mapDefinitions;
    }

    public MapDefinition loadMapDefinition(File map) throws IOException {
        return mapConverter.convertRawMapToDefinition(Integer.valueOf(map.getName().replace(".json","")),objectMapper.readValue(map, RawMap.class));
    }
}
