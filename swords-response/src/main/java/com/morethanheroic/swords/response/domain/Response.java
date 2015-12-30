package com.morethanheroic.swords.response.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.*;
import java.util.HashMap;

@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE
)
public class Response {

    @JsonProperty("charinfo")
    private final CharacterData characterData;

    @JsonProperty("data")
    private final HashMap<String, Object> data;

    public Response(CharacterData characterData) {
        this.characterData = characterData;
        this.data = new HashMap<>();
    }

    public void setData(String name, Object value) {
        data.put(name, value);
    }

    public Object getData(String name) {
        return data.get(name);
    }

    public HashMap<String, Object> getDataMap() {
        return data;
    }
}
