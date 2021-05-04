package de.buun.haven.lang.json;

import lombok.Setter;

public class ClickMessageEvent implements MessageEvent{

    @Setter
    private String text;
    private final MessageEventType eventType;
    @Setter
    private String[] value;

    public ClickMessageEvent(MessageEventType eventType){
        this.eventType = eventType;
    }

    @Override
    public String toJSON() {
        String json = "{\"text\":\"" + text + "\",";
        json = json + jsonEvent("clickEvent", eventType.getJsonMessage(), value[0]);
        json = json + "}";
        return json;
    }

}
