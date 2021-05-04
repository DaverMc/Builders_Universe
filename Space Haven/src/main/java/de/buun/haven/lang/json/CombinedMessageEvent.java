package de.buun.haven.lang.json;

public class CombinedMessageEvent implements MessageEvent{

    private String json;

    public CombinedMessageEvent(String text, MessageEventType clickEvent,
                                String clickValue, MessageEventType hoverEvent, String hoverValue){

        json = "{\"text\":\"" + text + "\",";
        json = json + jsonEvent("hoverEvent", hoverEvent.getJsonMessage(), hoverValue) + ",";
        json = json + jsonEvent("clickEvent", clickEvent.getJsonMessage(), clickValue) + "}";
    }

    @Override
    public String toJSON() {
        return json;
    }
}
