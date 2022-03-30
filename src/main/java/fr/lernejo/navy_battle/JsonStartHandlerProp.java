package fr.lernejo.navy_battle;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;


public class JsonStartHandlerProp {
    public final String id;
    public final String url;
    public final String message;

    public JsonStartHandlerProp(@JsonProperty("id") JsonNode id,
                                @JsonProperty("url") JsonNode url,
                                @JsonProperty("message") JsonNode message) {
        this.id = id.toString();
        this.url = url.toString();
        this.message = message.toString();
    }

}
