package de.buun.haven.lang.json;

import lombok.Setter;

public class HoverMessageEvent implements MessageEvent{

    @Setter
    private String text;
    private final MessageEventType eventType;
    @Setter
    private String[] value;

    public HoverMessageEvent(MessageEventType eventType){
        this.eventType = eventType;
    }

    @Override
    public String toJSON() {
        String json = "{\"text\":\"" + text + "\",";
        json = json + jsonEvent("hoverEvent", eventType.getJsonMessage(), value[0]);
        json = json + "}";
        return json;
    }

}
