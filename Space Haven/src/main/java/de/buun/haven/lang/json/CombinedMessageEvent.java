package de.buun.haven.lang.json;

import lombok.Setter;

public class CombinedMessageEvent implements MessageEvent{


    @Setter
    private String text;
    private final MessageEventType clickType;
    private final MessageEventType hoverType;
    @Setter
    private String[] value;

    public CombinedMessageEvent(MessageEventType clickType, MessageEventType hoverType){
        this.clickType = clickType;
        this.hoverType = hoverType;
    }

    @Override
    public String toJSON() {
        String json = "{\"text\":\"" + text + "\",";
        json = json + jsonEvent("hoverEvent", hoverType.getJsonMessage(), value[0]) + ",";
        json = json + jsonEvent("clickEvent", clickType.getJsonMessage(), value[1]) + "}";
        return json;
    }
}
