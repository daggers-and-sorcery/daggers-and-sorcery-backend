package com.morethanheroic.swords.response.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.HashMap;
import java.util.Map;

/**
 * Holds the data of a response.
 */
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE)
@SuppressFBWarnings("URF_UNREAD_FIELD")
public class Response {

    @JsonProperty("charinfo")
    private final CharacterData characterData;

    @JsonProperty("data")
    private final Map<String, Object> data = new HashMap<>();

    public Response(CharacterData characterData) {
        this.characterData = characterData;
    }

    public void setData(String name, Object value) {
        data.put(name, value);
    }

    public Object getData(String name) {
        return data.get(name);
    }

    public Map<String, Object> getDataMap() {
        return data;
    }
}
