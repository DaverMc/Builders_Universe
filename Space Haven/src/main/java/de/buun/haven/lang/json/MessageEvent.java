package de.buun.haven.lang.json;

public interface MessageEvent {

    String toJSON();

    default String jsonEvent(String name, String actionType, String value){
        return "\"" + name + "\":{\"action\":\"" + actionType + "\",\"value\":\"" + value + "\"}";

    }
}
