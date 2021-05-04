package de.buun.haven.lang.json;

public class ClickMessageEvent implements MessageEvent{

    private String json;

    public ClickMessageEvent(String text, MessageEventType eventType, String value){
        json = "{\"text\":\"" + text + "\",";
        json = json + jsonEvent("clickEvent", eventType.getJsonMessage(), value) + "}";
    }

    @Override
    public String toJSON() {
        return json;
    }

}
