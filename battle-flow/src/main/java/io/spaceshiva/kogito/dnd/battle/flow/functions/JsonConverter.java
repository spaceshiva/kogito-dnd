package io.spaceshiva.kogito.dnd.battle.flow.functions;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.spaceshiva.kogito.dnd.battle.flow.Battle;

@ApplicationScoped
public class JsonConverter {

    @Inject
    ObjectMapper objectMapper;

    public Battle fromJson(final JsonNode json) {
        try {
            return objectMapper.treeToValue(json, Battle.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting workflow data payload into Battle context", e);
        }
    }

    public JsonNode toJson(final Battle battle) {
        try {
            return objectMapper.readTree(objectMapper.writeValueAsString(battle));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error Battle context into Json value", e);
        }
    }
}
