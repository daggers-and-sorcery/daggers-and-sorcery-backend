package com.morethanheroic.swords.response.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.morethanheroic.response.domain.Response;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Holds the data of a response.
 */
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE)
@SuppressFBWarnings("URF_UNREAD_FIELD")
public class CharacterRefreshResponse extends Response {

    @JsonProperty("charinfo")
    private final CharacterData characterData;

    public CharacterRefreshResponse(CharacterData characterData) {
        this.characterData = characterData;
    }
}
