package de.buun.haven.version.v1_16;

import de.buun.haven.lang.json.AbstractMessageBuilder;
import de.buun.haven.lang.json.MessageType;
import de.buun.haven.util.Reflections;
import net.minecraft.server.v1_16_R3.ChatMessageType;
import net.minecraft.server.v1_16_R3.IChatBaseComponent;
import net.minecraft.server.v1_16_R3.PacketPlayOutChat;

public class MessageBuilder116 implements AbstractMessageBuilder {

    @Override
    public Object createPacket(String json, MessageType type) {
        IChatBaseComponent component = IChatBaseComponent.ChatSerializer.a(json);
        PacketPlayOutChat packet = new PacketPlayOutChat();

        Reflections.setFieldValue(packet, "a", component);
        Reflections.setFieldValue(packet, "b", ChatMessageType.a(type.getPosition()));

        return packet;
    }

}
