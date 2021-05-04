package de.buun.haven.version.v1_8;

import de.buun.haven.lang.json.AbstractMessageBuilder;
import de.buun.haven.lang.json.MessageType;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

public class MessageBuilder18 implements AbstractMessageBuilder {

    @Override
    public Object createPacket(String json, MessageType type) {
        IChatBaseComponent component = IChatBaseComponent.ChatSerializer.a(json);
        return new PacketPlayOutChat(component, type.getPosition());
    }
}
