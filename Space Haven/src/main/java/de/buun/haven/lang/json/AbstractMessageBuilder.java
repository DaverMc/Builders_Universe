package de.buun.haven.lang.json;

public interface AbstractMessageBuilder {

   Object createPacket(String json, MessageType type);

}
