package de.buun.haven.lang.json;

import de.buun.haven.util.PacketSender;
import de.buun.haven.version.VersionManager;
import de.buun.haven.version.v1_16.MessageBuilder116;
import de.buun.haven.version.v1_8.MessageBuilder18;
import org.bukkit.entity.Player;

public class MessageBuilder {

    private String json;
    private final AbstractMessageBuilder core;
    private final MessageType type;

    public MessageBuilder(MessageType type){
        this.type = type;
        this.core = VersionManager.newInstance(AbstractMessageBuilder.class, MessageBuilder18.class, MessageBuilder116.class);
        json = "[\"\"";
    }

    public void send(Player player){
        finish();
        PacketSender.send(player, this.core.createPacket(this.json, type));
    }

    public MessageBuilder append(String text){
        String textJson = ",{\"text\": \"" + text + "\"}";
        json = json + textJson;
        return this;
    }

    public MessageBuilder append(MessageEvent event){
        json = json + "," + event.toJSON();
        return this;
    }

    public void finish(){
        json = json + "]";
    }

}
