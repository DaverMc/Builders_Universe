package de.buun.haven.lang.json;

public class HoverMessageEvent implements MessageEvent{

    private String json;

    public HoverMessageEvent(String text, MessageEventType eventType, String value){
        json = "{\"text\":\"" + text + "\",";
        json = json + jsonEvent("hoverEvent", eventType.getJsonMessage(), value);
        json = json + "}";
    }

    @Override
    public String toJSON() {
        return json;
    }
}
